package cz.tyckouni.mvpgate.user.business

import cz.tyckouni.mvpgate.user.persistence.User
import java.util.Optional
import java.util.UUID

interface UserService {
    fun findByGuid(guid: UUID): Optional<User>

    fun findUsersByFirstName(firstName: String): List<User>

    fun findUsersByLastName(lastName: String): List<User>

    fun create(firstName: String, lastName: String): User

    fun delete(guid: UUID)
}
