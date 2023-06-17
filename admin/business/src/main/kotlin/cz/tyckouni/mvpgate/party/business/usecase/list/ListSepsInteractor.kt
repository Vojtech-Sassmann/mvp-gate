package cz.tyckouni.mvpgate.party.business.usecase.list

import cz.tyckouni.mvpgate.entity.Sep
import cz.tyckouni.mvpgate.party.business.gateway.storage.sep.SepFind
import cz.tyckouni.mvpgate.party.business.request.PageRequest
import cz.tyckouni.mvpgate.party.business.usecase.list.sort.Page
import cz.tyckouni.mvpgate.party.business.usecase.list.sort.SepSort

/**
 * Interactor of the [ListUseCase] for [Sep] entity.
 */
class ListSepsInteractor(
    private val sepFind: SepFind,
) : ListUseCase<Sep, SepSort> {
    override fun list(pageRequest: PageRequest<SepSort>): Page<Sep> {
        return sepFind.find(pageRequest)
    }
}
