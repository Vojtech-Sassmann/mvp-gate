package cz.tyckouni.mvpgate.admin.business.usecase.list

import cz.tyckouni.mvpgate.admin.business.gateway.storage.sep.SepFind
import cz.tyckouni.mvpgate.admin.business.input.PageInput
import cz.tyckouni.mvpgate.admin.business.usecase.list.sort.Page
import cz.tyckouni.mvpgate.admin.business.usecase.list.sort.SepSort
import cz.tyckouni.mvpgate.entity.Sep

/**
 * Interactor of the [ListUseCase] for [Sep] entity.
 */
class ListSepsInteractor(
    private val sepFind: SepFind,
) : ListUseCase<Sep, SepSort> {
    override fun list(pageInput: PageInput<SepSort>): Page<Sep> {
        return sepFind.find(pageInput)
    }
}
