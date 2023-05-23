package cz.tyckouni.mvpgate.party.business.usecase.create.idp

import cz.tyckouni.mvpgate.party.business.dao.GuidProvider
import cz.tyckouni.mvpgate.party.business.dao.idp.Idps
import cz.tyckouni.mvpgate.party.business.entity.CommonIdp
import cz.tyckouni.mvpgate.party.business.entity.Idp

class CreateIdpInteractor(
    private val idps: Idps,
    private val guidProvider: GuidProvider,
) : CreateIdpUseCase {

    override fun create(createIdpRequest: CreateIdpRequest): Idp {
        val newIdp = CommonIdp(guidProvider.newGuid(), createIdpRequest.name, createIdpRequest.loginUrl)

        idps.save(newIdp)

        return newIdp
    }
}
