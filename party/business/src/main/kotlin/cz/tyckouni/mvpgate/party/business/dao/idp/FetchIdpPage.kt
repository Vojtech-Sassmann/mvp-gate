package cz.tyckouni.mvpgate.party.business.dao.idp

import cz.tyckouni.mvpgate.party.business.entity.Idp
import cz.tyckouni.mvpgate.party.business.usecase.list.Page
import cz.tyckouni.mvpgate.party.business.usecase.list.PageRequest

interface FetchIdpPage {
    fun find(pageRequest: PageRequest): Page<Idp>
}
