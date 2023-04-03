package cz.tyckouni.mvpgate.user.grpc.converter

import cz.tyckouni.mvpgate.user.grpc.UserGrpc
import cz.tyckouni.mvpgate.user.grpc.UsersGrpc
import cz.tyckouni.mvpgate.user.persistence.User

/**
 * Converter between DB Users and gRPC Users.
 */
interface UserGrpcConverter {

    /**
     * Converts the given db User into a gRPC User.
     *
     * @return converted gRPC User
     * @property dbUser DB User type
     */
    fun toGrpc(dbUser: User): UserGrpc

    /**
     * Converts the given list of db Users into a gRPC repeated Users.
     *
     * @return converted repeated gRPC Users
     * @property dbUsers List of DB Users
     */
    fun toListGrpc(dbUsers: List<User>): UsersGrpc
}
