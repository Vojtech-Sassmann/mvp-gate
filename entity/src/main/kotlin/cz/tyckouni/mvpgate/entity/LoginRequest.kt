package cz.tyckouni.mvpgate.entity

/**
 * Representation of initiated login request from an end user.
 */
interface LoginRequest {

    /**
     * Get globally unique identifier of the login request.
     */
    fun getGuid(): String

    /**
     * Get url to which the end user should be redirected after successful login.
     */
    fun getRedirectUrl(): String

    /**
     * Identifier of the service provider to which the end user is trying to log in.
     */
    fun getSepGuid(): String
}
