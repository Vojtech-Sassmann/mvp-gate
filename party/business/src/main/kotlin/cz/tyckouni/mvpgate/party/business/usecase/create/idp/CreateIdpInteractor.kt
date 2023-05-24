package cz.tyckouni.mvpgate.party.business.usecase.create.idp

import cz.tyckouni.mvpgate.party.business.entity.CommonIdp
import cz.tyckouni.mvpgate.party.business.entity.Idp
import cz.tyckouni.mvpgate.party.business.gateway.GuidProvider
import cz.tyckouni.mvpgate.party.business.gateway.Idps

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
