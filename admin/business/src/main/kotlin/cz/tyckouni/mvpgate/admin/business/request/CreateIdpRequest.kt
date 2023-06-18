package cz.tyckouni.mvpgate.admin.business.request

import cz.tyckouni.mvpgate.admin.business.usecase.create.CreateIdpUseCase

/**
 * Input for the [CreateIdpUseCase]
 */
data class CreateIdpRequest(val name: String, val loginUrl: String)
