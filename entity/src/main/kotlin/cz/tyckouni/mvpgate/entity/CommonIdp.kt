package cz.tyckouni.mvpgate.entity

/**
 * Common implementation of [Idp]
 */
internal class CommonIdp(
    private val guid: String,
    private val name: String,
    private val loginUrl: String,
) : Idp {
    override fun getGuid(): String = guid

    override fun getName(): String = name

    override fun getLoginUrl(): String = loginUrl

    override fun toString(): String {
        return "CommonIdp(guid='$guid', name='$name', loginUrl='$loginUrl')"
    }
}
