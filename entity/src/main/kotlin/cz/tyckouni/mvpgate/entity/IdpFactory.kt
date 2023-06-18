package cz.tyckouni.mvpgate.entity

/**
 * Factory used to create general [Idp] entity
 */
class IdpFactory {
    companion object {
        fun create(guid: String, name: String, loginUrl: String): Idp = IdpImpl(guid, name, loginUrl)
    }
}
