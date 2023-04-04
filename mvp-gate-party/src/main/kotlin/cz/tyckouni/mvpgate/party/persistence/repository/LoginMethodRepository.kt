package cz.tyckouni.mvpgate.party.persistence.repository

import cz.tyckouni.mvpgate.party.persistence.entity.LoginMethod

/**
 * Persistence repository for the [LoginMethod] JPA entity
 */
interface LoginMethodRepository : PartyRepository<LoginMethod> {

    /**
     * Find all login methods for given Service provider by its id
     */
    fun findBySpId(spId: Long): Set<LoginMethod>
}
