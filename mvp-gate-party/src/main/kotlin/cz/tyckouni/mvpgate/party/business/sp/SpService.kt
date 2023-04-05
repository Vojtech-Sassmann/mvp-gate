package cz.tyckouni.mvpgate.party.business.sp

import cz.tyckouni.mvpgate.party.business.Service
import cz.tyckouni.mvpgate.party.persistence.sp.Sp
import java.util.*

interface SpService : Service<Sp> {

    fun findByName(name: String): Set<Sp>

    fun create(name: String): Sp

    fun delete(guid: UUID)
}
