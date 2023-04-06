package cz.tyckouni.mvpgate.party.persistence.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.NoRepositoryBean
import java.util.Optional
import java.util.UUID

/**
 * Base interface for all party DB repositories.
 *
 * @property T DB entity type
 */
@NoRepositoryBean
interface PartyRepository<T> : JpaRepository<T, Long> {

    /**
     * Find entity by its guid
     *
     * @param guid entity guid used for the search
     */
    fun findByGuid(guid: UUID): Optional<T>
}
