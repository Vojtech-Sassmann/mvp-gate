package cz.tyckouni.mvpgate.admin.business.input

import cz.tyckouni.mvpgate.admin.business.usecase.create.CreateIdpUseCase

/**
 * Input for the [CreateIdpUseCase]
 */
data class CreateIdpInput(val name: String, val loginUrl: String)
