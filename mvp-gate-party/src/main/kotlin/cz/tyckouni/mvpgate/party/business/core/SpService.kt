package cz.tyckouni.mvpgate.party.business.core

import cz.tyckouni.mvpgate.party.persistence.entity.Sp
import java.util.UUID

interface SpService : Service<Sp> {

    fun findByName(name: String): Set<Sp>

    fun create(name: String): Sp

    fun delete(guid: UUID)
}