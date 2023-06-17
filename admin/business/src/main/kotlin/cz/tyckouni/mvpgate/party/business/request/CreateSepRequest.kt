package cz.tyckouni.mvpgate.party.business.request

import cz.tyckouni.mvpgate.party.business.usecase.create.CreateSepUseCase

/**
 * Input for the [CreateSepUseCase]
 */
data class CreateSepRequest(val name: String, val redirectUrls: Set<String>)
