package cz.tyckouni.mvpgate.authentication.business.usecase.login

/**
 * Throw to indicate an error when initiating login.
 */
open class InvalidLoginAttemptException(message: String) : RuntimeException(message)
