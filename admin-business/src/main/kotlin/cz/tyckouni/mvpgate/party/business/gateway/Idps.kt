package cz.tyckouni.mvpgate.party.business.gateway

import cz.tyckouni.mvpgate.entity.Idp
import cz.tyckouni.mvpgate.party.business.usecase.list.Page
import cz.tyckouni.mvpgate.party.business.usecase.list.PageRequest
import cz.tyckouni.mvpgate.party.business.usecase.list.idp.IdpSort

interface Idps {

    /**
     * Persist given [Idp]
     */
    fun save(idp: Idp)

    /**
     * Fetch from storage a [Page] of [Idp] by given [pageRequest]
     *
     * @return found [Page]
     */
    fun find(pageRequest: PageRequest<IdpSort>): Page<Idp>

    /**
     * Returns true, if there is an [Idp] with given [name]
     *
     * @return true, if there is an [Idp] found, false otherwise
     */
    fun existsByName(name: String): Boolean
}
