package cz.tyckouni.mvpgate.party.persistence.entity

import jakarta.persistence.Column
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import java.util.UUID

/**
 * Base class of all party DB entities.
 */
@MappedSuperclass
open class PartyEntity(
    @Column(name = "guid", nullable = false)
    open var guid: UUID = UUID.randomUUID(),

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Long? = null,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PartyEntity

        if (guid != other.guid) return false

        return true
    }

    override fun hashCode(): Int {
        return guid.hashCode()
    }
}
