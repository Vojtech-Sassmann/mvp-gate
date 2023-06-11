package cz.tyckouni.mvpgate.party.business.gateway

import cz.tyckouni.mvpgate.entity.Sep
import cz.tyckouni.mvpgate.party.business.usecase.list.Page
import cz.tyckouni.mvpgate.party.business.usecase.list.PageRequest
import cz.tyckouni.mvpgate.party.business.usecase.list.sep.SepSort

interface Seps {

    /**
     * Persist the given [sep]
     *
     * @param sep to be saved
     */
    fun save(sep: Sep)

    /**
     * Check if a [Sep] exists with given [name]
     *
     * @param name used for the search
     * @return true if exists, false otherwise
     */
    fun existsByName(name: String): Boolean

    /**
     * Fetch from storage a [Page] of [Sep] by given [pageRequest]
     *
     * @return found [Page]
     */
    fun find(pageRequest: PageRequest<SepSort>): Page<Sep>
}
