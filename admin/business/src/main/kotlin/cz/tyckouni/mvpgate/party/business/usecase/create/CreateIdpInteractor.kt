package cz.tyckouni.mvpgate.party.business.usecase.create

import cz.tyckouni.mvpgate.entity.Idp
import cz.tyckouni.mvpgate.entity.IdpFactory
import cz.tyckouni.mvpgate.party.business.gateway.GuidProvider
import cz.tyckouni.mvpgate.party.business.gateway.storage.idp.IdpExistsByName
import cz.tyckouni.mvpgate.party.business.gateway.storage.idp.IdpSave
import cz.tyckouni.mvpgate.party.business.request.CreateIdpRequest
import cz.tyckouni.mvpgate.party.business.usecase.validation.UrlValidator
import cz.tyckouni.mvpgate.party.business.usecase.validation.Validator

/**
 * Interactor of the [CreateIdpUseCase]
 */
class CreateIdpInteractor(
    private val idpSave: IdpSave,
    private val idpExistsByName: IdpExistsByName,
    private val guidProvider: GuidProvider,
) : CreateIdpUseCase {

    override fun create(createIdpRequest: CreateIdpRequest): Idp {
        Validator()
            .validate(
                UrlValidator.isValidUrl(createIdpRequest.loginUrl),
                "invalid login URL: '${createIdpRequest.loginUrl}'",
            )
            .validate(createIdpRequest.name.isNotBlank(), "name cannot be blank")
            .validate(!idpExistsByName.existsByName(createIdpRequest.name), "given name is not unique: '${createIdpRequest.name}'")
            .handle()

        val newIdp = IdpFactory.create(guidProvider.newGuid(), createIdpRequest.name, createIdpRequest.loginUrl)

        idpSave.save(newIdp)

        return newIdp
    }
}
