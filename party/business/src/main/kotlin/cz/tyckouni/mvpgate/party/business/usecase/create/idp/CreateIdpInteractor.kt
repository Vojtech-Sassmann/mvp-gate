package cz.tyckouni.mvpgate.party.business.usecase.create.idp

import cz.tyckouni.mvpgate.party.business.dao.GuidProvider
import cz.tyckouni.mvpgate.party.business.dao.idp.SaveIdp
import cz.tyckouni.mvpgate.party.business.entity.Idp

class CreateIdpInteractor(
    private val saveIdp: SaveIdp,
    private val guidProvider: GuidProvider,
) : CreateIdpUseCase {

    override fun create(createIdpInput: CreateIdpInput): Idp {
        val newIdp = Idp(guidProvider.newGuid(), createIdpInput.name, createIdpInput.loginUrl)

        saveIdp.save(newIdp)

        return newIdp
    }
}
