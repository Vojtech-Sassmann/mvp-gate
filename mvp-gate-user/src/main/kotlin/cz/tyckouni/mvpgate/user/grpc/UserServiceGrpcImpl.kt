package cz.tyckouni.mvpgate.user.grpc

import com.google.protobuf.Empty
import cz.tyckouni.mvpgate.user.business.UserService
import cz.tyckouni.mvpgate.user.grpc.converter.UserGrpcConverter
import io.micrometer.tracing.Tracer
import org.lognet.springboot.grpc.GRpcService
import org.slf4j.LoggerFactory
import java.util.UUID

/**
 * Implementation of the [UserServiceGrpc] service
 */
@GRpcService
class UserServiceGrpcImpl(
    private val userService: UserService,
    private val converter: UserGrpcConverter,
    private val tracer: Tracer,
) : UserServiceGrpcKt.UserServiceCoroutineImplBase() {
    override suspend fun findByGuid(request: ByGuidRequest): UserGrpc {
        val span = tracer.startScopedSpan("USER.FIND_BY_GUID")

        LOGGER.debug("Call to userService.findByGuid: {}", request)

        val user = userService.findByGuid(UUID.fromString(request.guid))
            .map(converter::toGrpc)
            .orElse(UserGrpc.getDefaultInstance())

        span.end()

        return user
    }

    override suspend fun findUsersByFirstName(request: ByFirstNameRequest): UsersGrpc {
        val span = tracer.startScopedSpan("USER.FIND_USERS_BY_FIRSTNAME")

        LOGGER.debug("Call to userService.findUsersByFirstName: {}", request)

        val users = converter.toListGrpc(userService.findUsersByFirstName(request.firstName))

        span.end()

        return users
    }

    override suspend fun findUsersByLastName(request: ByLastNameRequest): UsersGrpc {
        val span = tracer.startScopedSpan("USER.FIND_USERS_BY_FIRSTNAME")

        LOGGER.debug("Call to userService.findUsersByLastName: {}", request)

        val users = converter.toListGrpc(userService.findUsersByLastName(request.lastName))

        span.end()

        return users
    }

    override suspend fun delete(request: ByGuidRequest): Empty {
        val span = tracer.startScopedSpan("USER.DELETE")

        LOGGER.debug("Call to userService.delete: {}", request)

        try {
            userService.delete(UUID.fromString(request.guid))
        } catch (e: Exception) {
            LOGGER.debug(e.stackTraceToString())
            throw e
        }

        span.end()

        return Empty.newBuilder().build()
    }

    override suspend fun create(request: CreateRequest): UserGrpc {
        val span = tracer.startScopedSpan("USER.CREATE")

        LOGGER.debug("Call to userService.create: {}", request)

        val user = converter.toGrpc(userService.create(request.firstName, request.lastName))

        span.end()

        return user
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(UserServiceGrpcImpl::class.java)
    }
}
