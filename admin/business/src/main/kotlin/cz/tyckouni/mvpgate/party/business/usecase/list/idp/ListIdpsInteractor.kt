package cz.tyckouni.mvpgate.party.business.usecase.list.idp

import cz.tyckouni.mvpgate.entity.Idp
import cz.tyckouni.mvpgate.party.business.gateway.storage.idp.IdpFind
import cz.tyckouni.mvpgate.party.business.usecase.list.ListUseCase
import cz.tyckouni.mvpgate.party.business.usecase.list.Page
import cz.tyckouni.mvpgate.party.business.usecase.list.PageRequest

/**
 * Interactor of the [ListUseCase]
 */
class ListIdpsInteractor(
    private val idpFind: IdpFind,
) : ListUseCase<Idp, IdpSort> {
    override fun list(pageRequest: PageRequest<IdpSort>): Page<Idp> {
        return idpFind.find(pageRequest)
    }
}
