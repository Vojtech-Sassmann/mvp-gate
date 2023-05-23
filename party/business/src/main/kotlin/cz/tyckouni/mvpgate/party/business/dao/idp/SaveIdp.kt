package cz.tyckouni.mvpgate.party.business.dao.idp

import cz.tyckouni.mvpgate.party.business.entity.Idp

interface SaveIdp {

    /**
     * Persist given [Idp]
     */
    fun save(idp: Idp)
}
