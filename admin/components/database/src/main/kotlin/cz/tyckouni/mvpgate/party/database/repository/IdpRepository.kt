package cz.tyckouni.mvpgate.party.database.repository

import cz.tyckouni.mvpgate.party.database.entity.IdpJpa
import org.springframework.data.jpa.repository.JpaRepository

interface IdpRepository : JpaRepository<IdpJpa, Long> {

    /**
     * Check if there is an [IdpJpa] with given [name]
     *
     * @return true, if an [IdpJpa] with given [name] is found, false otherwise
     */
    fun existsByName(name: String): Boolean
}
