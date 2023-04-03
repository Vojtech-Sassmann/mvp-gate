package cz.tyckouni.mvpgate.user.grpc.converter

import cz.tyckouni.mvpgate.user.grpc.UserGrpc
import cz.tyckouni.mvpgate.user.grpc.UsersGrpc
import cz.tyckouni.mvpgate.user.persistence.User
import org.springframework.stereotype.Component

/**
 * Implementation of the [UserGrpcConverter] service
 */
@Component
class UserGrpcConverterImpl : UserGrpcConverter {
    override fun toGrpc(dbUser: User): UserGrpc {
        return UserGrpc.newBuilder()
            .setGuid(dbUser.guid.toString())
            .setFirstName(dbUser.firstName)
            .setLastName(dbUser.lastName)
            .build()
    }

    override fun toListGrpc(dbUsers: List<User>): UsersGrpc {
        var builder = UsersGrpc.newBuilder()
        dbUsers.forEach {
            builder.addUsers(toGrpc(it))
        }
        return builder.build()
    }
}
