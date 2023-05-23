package cz.tyckouni.mvpgate.party.business.entity

/**
 * Identity provider entity
 *
 * This entity represents a Party, which can provide an Identity for users.
 */
interface Idp {

    fun getGuid(): String

    fun getName(): String

    fun getLoginUrl(): String
}
