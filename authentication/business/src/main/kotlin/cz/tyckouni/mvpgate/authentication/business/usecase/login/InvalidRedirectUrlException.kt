package cz.tyckouni.mvpgate.authentication.business.usecase.login

/**
 * Thrown if the given redirect url cannot be used.
 */
class InvalidRedirectUrlException(message: String) : InvalidLoginAttemptException(message)
