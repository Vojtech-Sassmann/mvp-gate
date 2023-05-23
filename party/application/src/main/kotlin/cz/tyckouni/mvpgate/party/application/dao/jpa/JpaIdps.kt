package cz.tyckouni.mvpgate.party.application.dao.jpa

import cz.tyckouni.mvpgate.party.business.dao.idp.Idps
import cz.tyckouni.mvpgate.party.business.entity.CommonIdp
import cz.tyckouni.mvpgate.party.business.entity.Idp
import cz.tyckouni.mvpgate.party.business.usecase.list.Order
import cz.tyckouni.mvpgate.party.business.usecase.list.Page
import cz.tyckouni.mvpgate.party.business.usecase.list.PageRequest
import cz.tyckouni.mvpgate.party.business.usecase.list.idp.IdpSort
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Component

@Component
class JpaIdps(
    private val repository: IdpJpaRepository,
) : Idps {

    override fun save(idp: Idp) {
        val idpJpa = IdpJpa(
            guid = idp.getGuid(),
            name = idp.getName(),
            loginUrl = idp.getLoginUrl(),
        )

        repository.save(idpJpa)
    }

    override fun find(pageRequest: PageRequest<IdpSort>): Page<Idp> {
        val pageable = org.springframework.data.domain.PageRequest.of(
            pageRequest.number,
            pageRequest.size,
            getSortDirection(pageRequest.order),
            getSortProperty(pageRequest.sortProperty),
        )

        val jpaPage = repository.findAll(pageable)
            .map { idp -> convertToIdp(idp) }

        return Page(jpaPage.content, jpaPage.size)
    }

    private fun convertToIdp(idp: IdpJpa): Idp = CommonIdp(idp.guid, idp.name, idp.loginUrl)

    private fun getSortDirection(order: Order): Sort.Direction = when (order) {
        Order.ASCENDING -> Sort.Direction.ASC
        Order.DESCENDING -> Sort.Direction.DESC
    }

    private fun getSortProperty(idpSort: IdpSort): String = when (idpSort) {
        IdpSort.NAME -> "name"
    }
}
