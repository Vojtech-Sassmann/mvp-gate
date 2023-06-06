package cz.tyckouni.mvpgate.entity

/**
 * Identity provider entity
 *
 * This entity represents a Party, which can provide an Identity for users.
 */
interface Idp {

    /**
     * Get global unique identifier of the Idp
     */
    fun getGuid(): String

    /**
     * Get unique name of the Idp
     */
    fun getName(): String

    /**
     * URL used for login redirection
     */
    fun getLoginUrl(): String
}
