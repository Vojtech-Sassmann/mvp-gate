package cz.tyckouni.mvpgate.party.grpc

import com.google.protobuf.Empty
import cz.tyckouni.mvpgate.grpc.ByGuidRequest
import cz.tyckouni.mvpgate.party.business.sp.SpService
import cz.tyckouni.mvpgate.party.grpc.converter.sp.SpGrpcConverter
import io.micrometer.tracing.Tracer
import org.lognet.springboot.grpc.GRpcService
import org.slf4j.LoggerFactory
import java.util.UUID

/**
 * Implementation of the [SpServiceGrpc] gRPC service
 */
@GRpcService
class SpServiceGrpcImpl(
    private val spService: SpService,
    private val converter: SpGrpcConverter,
    private val tracer: Tracer,
) : SpServiceGrpcKt.SpServiceCoroutineImplBase() {

    override suspend fun findByGuid(request: ByGuidRequest): SpGrpc {
        val span = tracer.startScopedSpan("SP.FIND_BY_GUID")

        LOGGER.debug("Call to SpService.findByGuid: {}", request)

        val sp = spService.findByGuid(UUID.fromString(request.guid))
            .map(converter::toGrpc)
            .orElse(SpGrpc.getDefaultInstance())

        span.end()

        return sp
    }

    override suspend fun create(request: CreateSpRequest): SpGrpc {
        LOGGER.debug("Call to SpService.create: {}", request)

        val sp = converter.toGrpc(spService.create(request.name))

        return sp
    }

    override suspend fun delete(request: ByGuidRequest): Empty {
        LOGGER.debug("Call to SpService.delete: {}", request)

        spService.delete(UUID.fromString(request.guid))

        return Empty.newBuilder().build()
    }

    override suspend fun findByName(requst: ByNameRequest): SpsGrpc {
        LOGGER.debug("Call to SpService.findByName: {}", requst)

        val sps = converter.toListGrpc(spService.findByName(requst.name).toList())

        return sps
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(SpServiceGrpcImpl::class.java)
    }
}
