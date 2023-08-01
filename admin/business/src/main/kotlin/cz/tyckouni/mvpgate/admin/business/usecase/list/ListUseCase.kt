package cz.tyckouni.mvpgate.admin.business.usecase.list

import cz.tyckouni.mvpgate.admin.business.input.PageInput
import cz.tyckouni.mvpgate.admin.business.usecase.list.sort.Page
import cz.tyckouni.mvpgate.admin.business.usecase.list.sort.Sort

fun interface ListUseCase<T, S : Sort<T>> {

    /**
     * Find a page of [T] by given [pageInput]
     *
     * @return [Page] with list of found [T]
     */
    fun list(pageInput: PageInput<S>): Page<T>
}
