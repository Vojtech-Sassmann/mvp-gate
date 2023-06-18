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

    override fun create(createIdpInput: cz.tyckouni.mvpgate.admin.business.input.CreateIdpInput): Idp {
        Validator()
            .validate(
                UrlValidator.isValidUrl(createIdpInput.loginUrl),
                "invalid login URL: '${createIdpInput.loginUrl}'",
            )
            .validate(createIdpInput.name.isNotBlank(), "name cannot be blank")
            .validate(!idpExistsByName.existsByName(createIdpInput.name), "given name is not unique: '${createIdpInput.name}'")
            .handle()

        val newIdp = IdpFactory.create(guidProvider.newGuid(), createIdpInput.name, createIdpInput.loginUrl)

        idpSave.save(newIdp)

        return newIdp
    }
}
