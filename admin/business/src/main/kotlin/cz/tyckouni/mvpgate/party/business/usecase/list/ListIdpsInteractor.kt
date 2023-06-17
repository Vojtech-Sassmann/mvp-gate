package cz.tyckouni.mvpgate.party.business.usecase.list

import cz.tyckouni.mvpgate.entity.Idp
import cz.tyckouni.mvpgate.party.business.gateway.storage.idp.IdpFind
import cz.tyckouni.mvpgate.party.business.request.PageRequest
import cz.tyckouni.mvpgate.party.business.usecase.list.sort.IdpSort
import cz.tyckouni.mvpgate.party.business.usecase.list.sort.Page

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
