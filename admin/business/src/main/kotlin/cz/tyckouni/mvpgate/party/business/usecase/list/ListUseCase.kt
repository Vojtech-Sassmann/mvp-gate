package cz.tyckouni.mvpgate.party.business.usecase.list

import cz.tyckouni.mvpgate.party.business.request.PageRequest
import cz.tyckouni.mvpgate.party.business.usecase.list.sort.Page
import cz.tyckouni.mvpgate.party.business.usecase.list.sort.Sort

fun interface ListUseCase<T, S : Sort<T>> {

    /**
     * Find a page of [T] by given [pageRequest]
     *
     * @return [Page] with list of found [T]
     */
    fun list(pageRequest: PageRequest<S>): Page<T>
}
