package cz.tyckouni.mvpgate.user.business

import cz.tyckouni.mvpgate.user.persistence.User
import cz.tyckouni.mvpgate.user.persistence.UserRepository
import org.springframework.stereotype.Service
import java.util.Objects
import java.util.Optional
import java.util.UUID

@Service
class UserServiceImpl(
    private val repository: UserRepository,
) : UserService {
    override fun findByGuid(guid: UUID): Optional<User> {
        return repository.findByGuid(Objects.requireNonNull(guid, "guid cannot be null while finding users"))
    }

    override fun findUsersByFirstName(firstName: String): List<User> {
        return repository.findUsersByFirstName(Objects.requireNonNull(firstName, "firstName cannot be null while finding users"))
    }

    override fun findUsersByLastName(lastName: String): List<User> {
        return repository.findUsersByLastName(Objects.requireNonNull(lastName, "lastName cannot be null while finding users"))
    }

    override fun create(firstName: String, lastName: String): User {
        Objects.requireNonNull(firstName, "firstName cannot be null while creating new user")
        Objects.requireNonNull(lastName, "lastName cannot be null while creating new user")
        return repository.save(User(firstName = firstName, lastName = lastName))
    }

    override fun delete(guid: UUID) {
        repository.delete(findByGuid(guid).get())
    }
}
