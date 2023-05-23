package cz.tyckouni.mvpgate.party.business.usecase.list.idp

import cz.tyckouni.mvpgate.party.business.dao.idp.Idps
import cz.tyckouni.mvpgate.party.business.entity.Idp
import cz.tyckouni.mvpgate.party.business.usecase.list.Page
import cz.tyckouni.mvpgate.party.business.usecase.list.PageRequest

internal class ListIdpsInteractor(
    private val idps: Idps,
) : ListIdpsUseCase {
    override fun list(pageRequest: PageRequest): Page<Idp> {
        return idps.find(pageRequest)
    }
}
