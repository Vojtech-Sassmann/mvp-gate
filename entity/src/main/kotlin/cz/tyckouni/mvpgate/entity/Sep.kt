package cz.tyckouni.mvpgate.entity

/**
 * Service provider
 *
 * This entity represents a relaying party which can use the
 * MVP gate for user authentication.
 */
interface Sep {

    /**
     * Get globally unique identifier
     */
    fun getGuid(): String

    /**
     * Get unique name
     */
    fun getName(): String

    /**
     * Get set of allowed redirect urls
     */
    fun getRedirectUrls(): Set<String>
}
