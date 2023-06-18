package cz.tyckouni.mvpgate.entity

/**
 * Factory for common [Sep] instances
 */
class SepFactory {
    companion object {
        fun create(guid: String, name: String, redirectUrls: Set<String>): Sep = SepImpl(guid, name, redirectUrls)
    }
}
