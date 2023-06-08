package cz.tyckouni.mvpgate.party.database.repository

import cz.tyckouni.mvpgate.party.database.entity.SepJpa
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Jpa repository for [SepJpa]
 */
interface SepJpaRepository : JpaRepository<SepJpa, Long> {

    /**
     * Checks if there is a [SepJpa] with given [name]
     *
     * @return true if a [SepJpa] is found, false otherwise
     */
    fun existsByName(name: String): Boolean
}
