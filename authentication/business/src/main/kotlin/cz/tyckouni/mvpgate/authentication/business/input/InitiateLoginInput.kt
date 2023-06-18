package cz.tyckouni.mvpgate.authentication.business.input

import cz.tyckouni.mvpgate.authentication.business.usecase.login.InitiateLoginUseCase

/**
 * Input for the [InitiateLoginUseCase]
 */
data class InitiateLoginInput(
    val sepGuid: String,
    val redirectUrl: String,
)
