package cz.tyckouni.mvpgate.party.business.gateway.storage.idp

import cz.tyckouni.mvpgate.entity.Idp

/**
 * Gateway for exists [Idp] exists by name operation
 */
fun interface IdpExistsByName {

    /**
     * Returns true, if there is an [Idp] with given [name]
     *
     * @return true, if there is an [Idp] found, false otherwise
     */
    fun existsByName(name: String): Boolean
}
