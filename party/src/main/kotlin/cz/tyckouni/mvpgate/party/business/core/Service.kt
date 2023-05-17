package cz.tyckouni.mvpgate.party.business.core

import cz.tyckouni.mvpgate.party.persistence.entity.PartyEntity
import java.util.Optional
import java.util.UUID

/**
 * Business service base interface
 */
interface Service<T : PartyEntity> {

    /**
     * Find entity by its [guid]
     *
     * @return Optional with found entity, or empty
     */
    fun findByGuid(guid: UUID): Optional<T>
}
