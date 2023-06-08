package cz.tyckouni.mvpgate.party.business.usecase.create

import cz.tyckouni.mvpgate.entity.Sep
import cz.tyckouni.mvpgate.entity.SepFactory
import cz.tyckouni.mvpgate.party.business.gateway.GuidProvider
import cz.tyckouni.mvpgate.party.business.gateway.Seps
import cz.tyckouni.mvpgate.party.business.usecase.validation.UrlValidator
import cz.tyckouni.mvpgate.party.business.usecase.validation.Validator

class CreateSepInteractor(
    private val seps: Seps,
    private val guidProvider: GuidProvider,
): CreateSepUseCase {
    override fun create(createSepRequest: CreateSepRequest): Sep {
        val validator = Validator()
            .validate(createSepRequest.name.isNotBlank(), "name cannot be blank")
            .validate(createSepRequest.redirectUrls.isNotEmpty(), "redirect urls cannot be empty")
            .validate(!seps.existsByName(createSepRequest.name), "given name is not unique: '${createSepRequest.name}'")

        createSepRequest.redirectUrls.forEach { redirectUrl ->
            validator.validate(
                UrlValidator.isValidUrl(redirectUrl),
                "invalid redirect URL: '$redirectUrl'"
            )
        }

        validator.handle()
        val sep = SepFactory.create(guidProvider.newGuid(), createSepRequest.name, createSepRequest.redirectUrls)

        seps.save(sep)

        return sep
    }
}