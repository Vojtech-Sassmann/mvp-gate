package cz.tyckouni.mvpgate.party.business.usecase.list.idp

import cz.tyckouni.mvpgate.party.business.entity.Idp
import cz.tyckouni.mvpgate.party.business.usecase.list.Page
import cz.tyckouni.mvpgate.party.business.usecase.list.PageRequest

interface ListIdpsUseCase {

    /**
     * Find a page of [Idp] by given [pageRequest]
     *
     * @return [Page] with list of found [Idp]
     */
    fun list(pageRequest: PageRequest<IdpSort>): Page<Idp>
}
