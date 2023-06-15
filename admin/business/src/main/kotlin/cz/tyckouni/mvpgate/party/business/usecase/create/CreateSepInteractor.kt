package cz.tyckouni.mvpgate.party.business.usecase.create

import cz.tyckouni.mvpgate.entity.Sep
import cz.tyckouni.mvpgate.entity.SepFactory
import cz.tyckouni.mvpgate.party.business.gateway.GuidProvider
import cz.tyckouni.mvpgate.party.business.gateway.storage.sep.SepExistsByName
import cz.tyckouni.mvpgate.party.business.gateway.storage.sep.SepSave
import cz.tyckouni.mvpgate.party.business.usecase.validation.UrlValidator
import cz.tyckouni.mvpgate.party.business.usecase.validation.Validator

/**
 * Interactor of the [CreateSepUseCase]
 */
class CreateSepInteractor(
    private val sepSave: SepSave,
    private val sepExistsByName: SepExistsByName,
    private val guidProvider: GuidProvider,
) : CreateSepUseCase {
    override fun create(createSepRequest: CreateSepRequest): Sep {
        val validator = Validator()
            .validate(createSepRequest.name.isNotBlank(), "name cannot be blank")
            .validate(createSepRequest.redirectUrls.isNotEmpty(), "redirect urls cannot be empty")
            .validate(!sepExistsByName.existsByName(createSepRequest.name), "given name is not unique: '${createSepRequest.name}'")

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
