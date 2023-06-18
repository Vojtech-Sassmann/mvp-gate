package cz.tyckouni.mvpgate.admin.business.usecase.create

import cz.tyckouni.mvpgate.admin.business.usecase.validation.UrlValidator
import cz.tyckouni.mvpgate.admin.business.usecase.validation.Validator
import cz.tyckouni.mvpgate.entity.Sep
import cz.tyckouni.mvpgate.entity.SepFactory

/**
 * Interactor of the [CreateSepUseCase]
 */
class CreateSepInteractor(
    private val sepSave: cz.tyckouni.mvpgate.admin.business.gateway.storage.sep.SepSave,
    private val sepExistsByName: cz.tyckouni.mvpgate.admin.business.gateway.storage.sep.SepExistsByName,
    private val guidProvider: cz.tyckouni.mvpgate.admin.business.gateway.GuidProvider,
) : CreateSepUseCase {
    override fun create(createSepRequest: cz.tyckouni.mvpgate.admin.business.request.CreateSepRequest): Sep {
        val validator = Validator()
            .validate(createSepRequest.name.isNotBlank(), "name cannot be blank")
            .validate(createSepRequest.redirectUrls.isNotEmpty(), "redirect urls cannot be empty")
            .validate(
                !sepExistsByName.existsByName(createSepRequest.name),
                "given name is not unique: '${createSepRequest.name}'",
            )

        createSepRequest.redirectUrls.forEach { redirectUrl ->
            validator.validate(
                UrlValidator.isValidUrl(redirectUrl),
                "invalid redirect URL: '$redirectUrl'",
            )
        }

        validator.handle()
        val sep = SepFactory.create(guidProvider.newGuid(), createSepRequest.name, createSepRequest.redirectUrls)

        sepSave.save(sep)

        return sep
    }
}
