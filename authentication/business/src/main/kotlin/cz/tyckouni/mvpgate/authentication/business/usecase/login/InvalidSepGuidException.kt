package cz.tyckouni.mvpgate.authentication.business.usecase.login

/**
 * Thrown if no Sep with given guid is found.
 */
class InvalidSepGuidException(message: String) : InvalidLoginAttemptException(message)
