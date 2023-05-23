package cz.tyckouni.mvpgate.party.business.dao.idp

import cz.tyckouni.mvpgate.party.business.entity.Idp
import cz.tyckouni.mvpgate.party.business.usecase.list.Page
import cz.tyckouni.mvpgate.party.business.usecase.list.PageRequest

interface FetchIdpPage {

    /**
     * Fetch from storage a [Page] of [Idp] by given [pageRequest]
     *
     * @return found [Page]
     */
    fun find(pageRequest: PageRequest): Page<Idp>
}
