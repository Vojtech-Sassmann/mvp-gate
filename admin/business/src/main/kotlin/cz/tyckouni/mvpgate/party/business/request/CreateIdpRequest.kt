package cz.tyckouni.mvpgate.party.business.request

import cz.tyckouni.mvpgate.party.business.usecase.create.CreateIdpUseCase

/**
 * Input for the [CreateIdpUseCase]
 */
data class CreateIdpRequest(val name: String, val loginUrl: String)
