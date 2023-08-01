package cz.tyckouni.mvpgate.admin.business.usecase.create

import cz.tyckouni.mvpgate.admin.business.gateway.GuidProvider
import cz.tyckouni.mvpgate.admin.business.gateway.storage.sep.SepExistsByName
import cz.tyckouni.mvpgate.admin.business.gateway.storage.sep.SepSave
import cz.tyckouni.mvpgate.admin.business.input.CreateSepInput
import cz.tyckouni.mvpgate.admin.business.usecase.validation.UrlValidator
import cz.tyckouni.mvpgate.admin.business.usecase.validation.Validator
import cz.tyckouni.mvpgate.entity.Sep
import cz.tyckouni.mvpgate.entity.SepFactory

/**
 * Interactor of the [CreateSepUseCase]
 */
class CreateSepInteractor(
    private val sepSave: SepSave,
    private val sepExistsByName: SepExistsByName,
    private val guidProvider: GuidProvider,
) : CreateSepUseCase {
    override fun create(createSepInput: CreateSepInput): Sep {
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
