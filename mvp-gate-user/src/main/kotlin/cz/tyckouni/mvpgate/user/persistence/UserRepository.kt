package cz.tyckouni.mvpgate.user.persistence

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import java.util.Optional
import java.util.UUID

interface UserRepository : JpaRepository<User, Long> {

    /**
     * Find user by its guid
     *
     * @param guid user guid used for the search
     */
    fun findByGuid(guid: UUID): Optional<User>

    /**
     * Find users by their first name
     *
     * @param firstName users first name used for the search
     */
    fun findUsersByFirstName(firstName: String): List<User>

    /**
     * Find users by their last name
     *
     * @param lastName users last name used for the search
     */
    fun findUsersByLastName(lastName: String): List<User>
}
