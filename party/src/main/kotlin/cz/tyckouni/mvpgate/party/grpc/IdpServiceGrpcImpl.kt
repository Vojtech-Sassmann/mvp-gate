package cz.tyckouni.mvpgate.party.grpc

import cz.tyckouni.mvpgate.grpc.ByGuidRequest
import cz.tyckouni.mvpgate.party.business.core.IdpService
import cz.tyckouni.mvpgate.party.grpc.converter.IdpGrpcConverter
import io.grpc.stub.StreamObserver
import org.lognet.springboot.grpc.GRpcService
import org.slf4j.LoggerFactory
import java.util.UUID

/**
 * Implementation of the [IdpServiceGrpc] gRPC service
 */
@GRpcService
class IdpServiceGrpcImpl(
    private val idpService: IdpService,
    private val converter: IdpGrpcConverter,
) : IdpServiceGrpc.IdpServiceImplBase() {

    override fun findByGuid(request: ByGuidRequest?, responseObserver: StreamObserver<IdpGrpc>?) {
        requireNotNull(request)
        requireNotNull(responseObserver)

        LOGGER.debug("Call to IdpService.findByGuid: {}", request)

        val idp = idpService.findByGuid(UUID.fromString(request.guid))
            .map(converter::toGrpc)
            .orElse(IdpGrpc.getDefaultInstance())

        responseObserver.onNext(idp)
        responseObserver.onCompleted()
    }
    companion object {
        private val LOGGER = LoggerFactory.getLogger(IdpServiceGrpcImpl::class.java)
    }
}
