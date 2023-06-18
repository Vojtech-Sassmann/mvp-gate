package cz.tyckouni.mvpgate.admin.business.usecase.list

import cz.tyckouni.mvpgate.admin.business.usecase.list.sort.Page
import cz.tyckouni.mvpgate.admin.business.usecase.list.sort.Sort

fun interface ListUseCase<T, S : Sort<T>> {

    /**
     * Find a page of [T] by given [pageRequest]
     *
     * @return [Page] with list of found [T]
     */
    fun list(pageRequest: cz.tyckouni.mvpgate.admin.business.request.PageRequest<S>): Page<T>
}
