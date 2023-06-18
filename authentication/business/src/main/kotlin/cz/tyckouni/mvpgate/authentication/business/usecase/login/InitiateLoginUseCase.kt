package cz.tyckouni.mvpgate.authentication.business.usecase.login

import cz.tyckouni.mvpgate.authentication.business.input.InitiateLoginInput
import cz.tyckouni.mvpgate.entity.LoginRequest

/**
 * Use case for end user login initiation. This use case is issued when a new
 * login request from the service provider is issued.
 */
fun interface InitiateLoginUseCase {

    /**
     * Initiate use login.
     *
     * Validates the login input data and if the data is valid, create a new loginRequest.
     *
     * @throws InvalidSepGuidException if the given sepGuid is invalid
     * @throws InvalidRedirectUrlException if the given redirect url cannot be used
     * @return created [LoginRequest]
     */
    fun initiateLogin(initiateLoginInput: InitiateLoginInput): LoginRequest
}
