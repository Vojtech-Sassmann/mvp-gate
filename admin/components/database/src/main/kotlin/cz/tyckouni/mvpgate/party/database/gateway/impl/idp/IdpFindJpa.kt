package cz.tyckouni.mvpgate.party.database.gateway.impl.idp

import cz.tyckouni.mvpgate.entity.Idp
import cz.tyckouni.mvpgate.party.business.gateway.storage.idp.IdpFind
import cz.tyckouni.mvpgate.party.business.request.PageRequest
import cz.tyckouni.mvpgate.party.business.usecase.list.sort.IdpSort
import cz.tyckouni.mvpgate.party.business.usecase.list.sort.Order
import cz.tyckouni.mvpgate.party.business.usecase.list.sort.Page
import cz.tyckouni.mvpgate.party.database.repository.IdpRepository
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Component

/**
 * Implementation of the [IdpFind] gateway
 */
@Component
class IdpFindJpa(
    private val idpRepository: IdpRepository,
) : IdpFind {
    override fun find(pageRequest: PageRequest<IdpSort>): Page<Idp> {
        val pageable = org.springframework.data.domain.PageRequest.of(
            pageRequest.page,
            pageRequest.size,
            getSortDirection(pageRequest.order),
            getSortProperty(pageRequest.sortProperty),
        )

        val jpaPage = idpRepository.findAll(pageable)

        return Page(jpaPage.content, jpaPage.totalElements)
    }

    private fun getSortDirection(order: Order): Sort.Direction = when (order) {
        Order.ASCENDING -> Sort.Direction.ASC
        Order.DESCENDING -> Sort.Direction.DESC
    }

    private fun getSortProperty(idpSort: IdpSort): String = when (idpSort) {
        IdpSort.NAME -> "name"
    }
}
