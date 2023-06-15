package cz.tyckouni.mvpgate.party.business.gateway.storage.sep

import cz.tyckouni.mvpgate.entity.Sep
import cz.tyckouni.mvpgate.party.business.usecase.list.Page
import cz.tyckouni.mvpgate.party.business.usecase.list.PageRequest
import cz.tyckouni.mvpgate.party.business.usecase.list.sep.SepSort

/**
 * Storage gateway for [Sep] find operation
 */
fun interface SepFind {

    /**
     * Fetch from storage a [Page] of [Sep] by given [pageRequest]
     *
     * @return found [Page]
     */
    fun find(pageRequest: PageRequest<SepSort>): Page<Sep>
}
