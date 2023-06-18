package cz.tyckouni.mvpgate.admin.business.gateway.storage.idp

import cz.tyckouni.mvpgate.entity.Idp

/**
 * Gateway for the save [Idp] operation
 */
fun interface IdpSave {

    /**
     * Persist given [Idp]
     */
    fun save(idp: Idp)
}
