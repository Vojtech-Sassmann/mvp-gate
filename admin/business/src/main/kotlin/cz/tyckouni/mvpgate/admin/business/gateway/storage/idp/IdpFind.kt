package cz.tyckouni.mvpgate.admin.business.gateway.storage.idp

import cz.tyckouni.mvpgate.admin.business.usecase.list.sort.IdpSort
import cz.tyckouni.mvpgate.admin.business.usecase.list.sort.Page
import cz.tyckouni.mvpgate.entity.Idp

/**
 * Gateway for [Idp] find operations
 */
fun interface IdpFind {

    /**
     * Fetch from storage a [Page] of [Idp] by given [pageRequest]
     *
     * @return found [Page]
     */
    fun find(pageRequest: cz.tyckouni.mvpgate.admin.business.request.PageRequest<IdpSort>): Page<Idp>
}
