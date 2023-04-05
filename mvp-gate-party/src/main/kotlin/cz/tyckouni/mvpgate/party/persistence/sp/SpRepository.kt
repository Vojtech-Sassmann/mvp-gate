package cz.tyckouni.mvpgate.party.persistence.sp

import cz.tyckouni.mvpgate.party.persistence.PartyRepository
import java.util.*

interface SpRepository : PartyRepository<Sp> {

    /**
     * Find ServiceProvider by its name
     *
     * @param name SP name used for search
     */
    fun findByName(name: String): Set<Sp>
}
