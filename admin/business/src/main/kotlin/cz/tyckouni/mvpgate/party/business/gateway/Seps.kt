package cz.tyckouni.mvpgate.party.business.gateway

import cz.tyckouni.mvpgate.entity.Sep

interface Seps {

    /**
     * Persist the given [sep]
     *
     * @param sep to be saved
     */
    fun save(sep: Sep)

    /**
     * Check if a [Sep] exists with given [name]
     *
     * @param name used for the search
     * @return true if exists, false otherwise
     */
    fun existsByName(name: String): Boolean
}
