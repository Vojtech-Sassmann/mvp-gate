package cz.tyckouni.mvpgate.party.business.entity

/**
 * Identity provider entity
 *
 * This entity represents a Party, which can provide an Identity for users.
 */
class Idp(
    val guid: String,
    val name: String,
    val loginUrl: String,
) {
    override fun toString(): String {
        return "Idp(guid='$guid', name='$name', loginUrl='$loginUrl')"
    }
}
