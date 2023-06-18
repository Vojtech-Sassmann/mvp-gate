package cz.tyckouni.mvpgate.admin.business.gateway.storage.sep

import cz.tyckouni.mvpgate.entity.Sep

/**
 * Storage gateway for [Sep] save operation
 */
fun interface SepSave {

    /**
     * Persist the given [sep]
     *
     * @param sep to be saved
     */
    fun save(sep: Sep)
}
