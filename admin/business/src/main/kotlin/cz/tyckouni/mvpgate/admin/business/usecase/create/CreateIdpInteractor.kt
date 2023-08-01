package cz.tyckouni.mvpgate.admin.business.usecase.create

import cz.tyckouni.mvpgate.admin.business.gateway.GuidProvider
import cz.tyckouni.mvpgate.admin.business.gateway.storage.idp.IdpExistsByName
import cz.tyckouni.mvpgate.admin.business.gateway.storage.idp.IdpSave
import cz.tyckouni.mvpgate.admin.business.input.CreateIdpInput
import cz.tyckouni.mvpgate.admin.business.usecase.validation.UrlValidator
import cz.tyckouni.mvpgate.admin.business.usecase.validation.Validator
import cz.tyckouni.mvpgate.entity.Idp
import cz.tyckouni.mvpgate.entity.IdpFactory

/**
 * Interactor of the [CreateIdpUseCase]
 */
class CreateIdpInteractor(
    private val idpSave: IdpSave,
    private val idpExistsByName: IdpExistsByName,
    private val guidProvider: GuidProvider,
) : CreateIdpUseCase {

    override fun create(createIdpInput: CreateIdpInput): Idp {
        Validator()
            .validate(
                UrlValidator.isValidUrl(createIdpInput.loginUrl),
                "invalid login URL: '${createIdpInput.loginUrl}'",
            )
            .validate(createIdpInput.name.isNotBlank(), "name cannot be blank")
            .validate(
                !idpExistsByName.existsByName(createIdpInput.name),
                "given name is not unique: '${createIdpInput.name}'",
            )
            .handle()

        val newIdp = IdpFactory.create(guidProvider.newGuid(), createIdpInput.name, createIdpInput.loginUrl)

        idpSave.save(newIdp)

        return newIdp
    }
}
