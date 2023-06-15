package cz.tyckouni.mvpgate.party.database.gateway.impl.sep

import cz.tyckouni.mvpgate.entity.Sep
import cz.tyckouni.mvpgate.party.business.gateway.storage.sep.SepFind
import cz.tyckouni.mvpgate.party.business.usecase.list.Order
import cz.tyckouni.mvpgate.party.business.usecase.list.Page
import cz.tyckouni.mvpgate.party.business.usecase.list.PageRequest
import cz.tyckouni.mvpgate.party.business.usecase.list.sep.SepSort
import cz.tyckouni.mvpgate.party.database.repository.SepRepository
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Component

/**
 * Jpa implementation of the [SepFindJpa] gateway
 */
@Component
class SepFindJpa(
    private val sepRepository: SepRepository,
) : SepFind {
    override fun find(pageRequest: PageRequest<SepSort>): Page<Sep> {
        val pageable = org.springframework.data.domain.PageRequest.of(
            pageRequest.page,
            pageRequest.size,
            getSortDirection(pageRequest.order),
            getSortProperty(pageRequest.sortProperty),
        )

        val jpaPage = sepRepository.findAll(pageable)

        return Page(jpaPage.content, jpaPage.totalElements)
    }

    private fun getSortDirection(order: Order): Sort.Direction = when (order) {
        Order.ASCENDING -> Sort.Direction.ASC
        Order.DESCENDING -> Sort.Direction.DESC
    }

    private fun getSortProperty(sepSort: SepSort): String = when (sepSort) {
        SepSort.NAME -> "name"
    }
}
