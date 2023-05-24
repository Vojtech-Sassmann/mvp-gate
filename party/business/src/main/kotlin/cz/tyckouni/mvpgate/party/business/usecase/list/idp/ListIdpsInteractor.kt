package cz.tyckouni.mvpgate.party.business.usecase.list.idp

import cz.tyckouni.mvpgate.party.business.entity.Idp
import cz.tyckouni.mvpgate.party.business.gateway.Idps
import cz.tyckouni.mvpgate.party.business.usecase.list.Page
import cz.tyckouni.mvpgate.party.business.usecase.list.PageRequest

/**
 * Interactor of the [ListIdpsUseCase]
 */
class ListIdpsInteractor(
    private val idps: Idps,
) : ListIdpsUseCase {
    override fun list(pageRequest: PageRequest<IdpSort>): Page<Idp> {
        return idps.find(pageRequest)
    }
}
