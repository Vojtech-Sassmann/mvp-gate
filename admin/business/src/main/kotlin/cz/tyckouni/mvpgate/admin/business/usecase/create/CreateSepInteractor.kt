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
    override fun create(createSepInput: cz.tyckouni.mvpgate.admin.business.input.CreateSepInput): Sep {
        val validator = Validator()
            .validate(createSepInput.name.isNotBlank(), "name cannot be blank")
            .validate(createSepInput.redirectUrls.isNotEmpty(), "redirect urls cannot be empty")
            .validate(
                !sepExistsByName.existsByName(createSepInput.name),
                "given name is not unique: '${createSepInput.name}'",
            )

        createSepInput.redirectUrls.forEach { redirectUrl ->
            validator.validate(
                UrlValidator.isValidUrl(redirectUrl),
                "invalid redirect URL: '$redirectUrl'",
            )
        }

        validator.handle()
        val sep = SepFactory.create(guidProvider.newGuid(), createSepInput.name, createSepInput.redirectUrls)

        sepSave.save(sep)

        return sep
    }
}
