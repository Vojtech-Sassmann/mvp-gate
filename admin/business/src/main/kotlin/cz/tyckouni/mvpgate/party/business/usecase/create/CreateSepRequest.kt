package cz.tyckouni.mvpgate.party.business.usecase.create

/**
 * Input for the [CreateSepUseCase]
 */
data class CreateSepRequest(val name: String, val redirectUrls: Set<String>)
