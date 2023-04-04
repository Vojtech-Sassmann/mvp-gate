package cz.tyckouni.mvpgate.party.business.core

import cz.tyckouni.mvpgate.party.persistence.entity.Sp
import java.util.UUID

/**
 * Business service for [Sp] entity management
 */
interface SpService : Service<Sp> {

    /**
     * Find all [Sp] with given name
     *
     * @return set of found [Sp]
     */
    fun findByName(name: String): Set<Sp>

    /**
     * Creates [Sp] with given name
     *
     * @return created [Sp]
     */
    fun create(name: String): Sp

    /**
     * Deletes [Sp] with given guid
     */
    fun delete(guid: UUID)
}