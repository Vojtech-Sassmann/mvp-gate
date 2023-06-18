package cz.tyckouni.mvpgate.admin.business.usecase.create

import cz.tyckouni.mvpgate.admin.business.usecase.validation.UrlValidator
import cz.tyckouni.mvpgate.admin.business.usecase.validation.Validator
import cz.tyckouni.mvpgate.entity.Idp
import cz.tyckouni.mvpgate.entity.IdpFactory

/**
 * Interactor of the [CreateIdpUseCase]
 */
class CreateIdpInteractor(
    private val idpSave: cz.tyckouni.mvpgate.admin.business.gateway.storage.idp.IdpSave,
    private val idpExistsByName: cz.tyckouni.mvpgate.admin.business.gateway.storage.idp.IdpExistsByName,
    private val guidProvider: cz.tyckouni.mvpgate.admin.business.gateway.GuidProvider,
) : cz.tyckouni.mvpgate.admin.business.usecase.create.CreateIdpUseCase {

    override fun create(createIdpRequest: cz.tyckouni.mvpgate.admin.business.request.CreateIdpRequest): Idp {
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
