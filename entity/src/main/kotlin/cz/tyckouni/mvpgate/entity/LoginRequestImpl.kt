package cz.tyckouni.mvpgate.entity

/**
 * Basic implementation of the [LoginRequestFactory]
 */
internal class LoginRequestImpl(
    private val guid: String,
    private val redirectUrl: String,
    private val sepGuid: String,
) : LoginRequest {

    override fun getGuid() = guid

    override fun getRedirectUrl() = redirectUrl

    override fun getSepGuid() = sepGuid
}
