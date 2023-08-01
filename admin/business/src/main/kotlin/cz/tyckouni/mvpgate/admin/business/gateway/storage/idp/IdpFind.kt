package cz.tyckouni.mvpgate.admin.business.gateway.storage.idp

import cz.tyckouni.mvpgate.admin.business.input.PageInput
import cz.tyckouni.mvpgate.admin.business.usecase.list.sort.IdpSort
import cz.tyckouni.mvpgate.admin.business.usecase.list.sort.Page
import cz.tyckouni.mvpgate.entity.Idp

/**
 * Gateway for [Idp] find operations
 */
fun interface IdpFind {

    /**
     * Fetch from storage a [Page] of [Idp] by given [pageInput]
     *
     * @return found [Page]
     */
    fun find(pageInput: PageInput<IdpSort>): Page<Idp>
}
