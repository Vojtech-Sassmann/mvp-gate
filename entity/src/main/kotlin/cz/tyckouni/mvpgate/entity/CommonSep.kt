package cz.tyckouni.mvpgate.entity

class CommonSep(
    private val guid: String,
    private val name: String,
    private val redirectUrls: Set<String>,
) : Sep {
    override fun getGuid(): String = guid

    override fun getName(): String = name

    override fun getRedirectUrls(): Set<String> = redirectUrls
    override fun toString(): String {
        return "CommonSep(guid='$guid', name='$name', redirectUrls=$redirectUrls)"
    }
}
