package cz.tyckouni.mvpgate.party.business.gateway.storage.sep

import cz.tyckouni.mvpgate.entity.Sep

/**
 * Storage gateway for [Sep] exists operations
 */
fun interface SepExistsByName {

    /**
     * Check if a [Sep] exists with given [name]
     *
     * @param name used for the search
     * @return true if exists, false otherwise
     */
    fun existsByName(name: String): Boolean
}
