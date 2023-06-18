package cz.tyckouni.mvpgate.admin.business.gateway.storage.sep

import cz.tyckouni.mvpgate.admin.business.usecase.list.sort.Page
import cz.tyckouni.mvpgate.admin.business.usecase.list.sort.SepSort
import cz.tyckouni.mvpgate.entity.Sep

/**
 * Storage gateway for [Sep] find operation
 */
fun interface SepFind {

    /**
     * Fetch from storage a [Page] of [Sep] by given [pageInput]
     *
     * @return found [Page]
     */
    fun find(pageInput: cz.tyckouni.mvpgate.admin.business.input.PageInput<SepSort>): Page<Sep>
}
