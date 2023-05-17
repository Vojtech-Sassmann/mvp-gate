package cz.tyckouni.mvpgate.party.persistence.repository

import cz.tyckouni.mvpgate.party.persistence.entity.Sp

interface SpRepository : PartyRepository<Sp> {

    /**
     * Find ServiceProvider by its name
     *
     * @param name SP name used for search
     */
    fun findByName(name: String): Set<Sp>
}
