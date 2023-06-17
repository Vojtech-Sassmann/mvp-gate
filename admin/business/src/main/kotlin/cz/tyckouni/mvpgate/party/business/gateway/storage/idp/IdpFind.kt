package cz.tyckouni.mvpgate.party.business.gateway.storage.idp

import cz.tyckouni.mvpgate.entity.Idp
import cz.tyckouni.mvpgate.party.business.request.PageRequest
import cz.tyckouni.mvpgate.party.business.usecase.list.sort.IdpSort
import cz.tyckouni.mvpgate.party.business.usecase.list.sort.Page

/**
 * Gateway for [Idp] find operations
 */
fun interface IdpFind {

    /**
     * Fetch from storage a [Page] of [Idp] by given [pageRequest]
     *
     * @return found [Page]
     */
    fun find(pageRequest: PageRequest<IdpSort>): Page<Idp>
}
