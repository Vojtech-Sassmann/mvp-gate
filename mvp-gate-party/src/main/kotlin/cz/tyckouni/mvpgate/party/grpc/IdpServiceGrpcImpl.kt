package cz.tyckouni.mvpgate.party.grpc

import cz.tyckouni.mvpgate.grpc.ByGuidRequest
import cz.tyckouni.mvpgate.party.business.core.IdpService
import cz.tyckouni.mvpgate.party.grpc.converter.IdpGrpcConverter
import io.micrometer.tracing.Tracer
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
    private val tracer: Tracer,
) : IdpServiceGrpcKt.IdpServiceCoroutineImplBase() {

    override suspend fun findByGuid(request: ByGuidRequest): IdpGrpc {
        val span = tracer.startScopedSpan("IDP.FIND_BY_GUID")

        LOGGER.debug("Call to IdpService.findByGuid: {}", request)

        val idp = idpService.findByGuid(UUID.fromString(request.guid))
            .map(converter::toGrpc)
            .orElse(IdpGrpc.getDefaultInstance())

        span.end()

        return idp
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(IdpServiceGrpcImpl::class.java)
    }
}
