package cz.tyckouni.mvpgate.entity

/**
 * Factory used to create basic [LoginRequest] entity instance
 */
class LoginRequestFactory {
    companion object {
        fun create(guid: String, redirectUrl: String, sepGuid: String): LoginRequest =
            LoginRequestImpl(
                guid = guid,
                redirectUrl = redirectUrl,
                sepGuid = sepGuid,
            )
    }
}
