package cz.tyckouni.mvpgate.party.business.usecase.create.idp

import cz.tyckouni.mvpgate.party.business.entity.CommonIdp
import cz.tyckouni.mvpgate.party.business.entity.Idp
import cz.tyckouni.mvpgate.party.business.gateway.GuidProvider
import cz.tyckouni.mvpgate.party.business.gateway.Idps
import cz.tyckouni.mvpgate.party.business.usecase.validation.UrlValidator
import cz.tyckouni.mvpgate.party.business.usecase.validation.Validator

/**
 * Interactor of the [CreateIdpUseCase]
 */
class CreateIdpInteractor(
    private val idps: Idps,
    private val guidProvider: GuidProvider,
) : CreateIdpUseCase {

    override fun create(createIdpRequest: CreateIdpRequest): Idp {
        Validator()
            .validate(UrlValidator.isValidUrl(createIdpRequest.loginUrl), "loginUrl is not a valid URL")
            .validate(createIdpRequest.name.isNotBlank(), "name cannot be blank")
            .validate(!idps.existsByName(createIdpRequest.name), "given name is not unique")
            .handle()

        val newIdp = CommonIdp(guidProvider.newGuid(), createIdpRequest.name, createIdpRequest.loginUrl)

        idps.save(newIdp)

        return newIdp
    }
}
