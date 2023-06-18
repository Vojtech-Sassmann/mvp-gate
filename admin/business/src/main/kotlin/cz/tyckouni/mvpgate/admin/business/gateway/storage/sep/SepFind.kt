package cz.tyckouni.mvpgate.admin.business.gateway.storage.sep

import cz.tyckouni.mvpgate.admin.business.usecase.list.sort.Page
import cz.tyckouni.mvpgate.admin.business.usecase.list.sort.SepSort
import cz.tyckouni.mvpgate.entity.Sep

/**
 * Storage gateway for [Sep] find operation
 */
fun interface SepFind {

    /**
     * Fetch from storage a [Page] of [Sep] by given [pageRequest]
     *
     * @return found [Page]
     */
    fun find(pageRequest: cz.tyckouni.mvpgate.admin.business.request.PageRequest<SepSort>): Page<Sep>
}
