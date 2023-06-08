package cz.tyckouni.mvpgate.party.business.usecase.create

/**
 * Input for the [CreateIdpUseCase]
 */
data class CreateIdpRequest(val name: String, val loginUrl: String)
