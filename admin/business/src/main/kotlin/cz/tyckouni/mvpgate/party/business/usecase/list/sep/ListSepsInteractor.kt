package cz.tyckouni.mvpgate.party.business.usecase.list.sep

import cz.tyckouni.mvpgate.entity.Sep
import cz.tyckouni.mvpgate.party.business.gateway.Seps
import cz.tyckouni.mvpgate.party.business.usecase.list.ListUseCase
import cz.tyckouni.mvpgate.party.business.usecase.list.Page
import cz.tyckouni.mvpgate.party.business.usecase.list.PageRequest

/**
 * Interactor of the [ListUseCase] for [Sep] entity.
 */
class ListSepsInteractor(
    val seps: Seps,
) : ListUseCase<Sep, SepSort> {
    override fun list(pageRequest: PageRequest<SepSort>): Page<Sep> {
        return seps.find(pageRequest)
    }
}