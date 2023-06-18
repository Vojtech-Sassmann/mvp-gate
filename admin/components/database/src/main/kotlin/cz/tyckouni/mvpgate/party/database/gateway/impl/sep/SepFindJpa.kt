package cz.tyckouni.mvpgate.party.database.gateway.impl.sep

import cz.tyckouni.mvpgate.admin.business.usecase.list.sort.Order
import cz.tyckouni.mvpgate.admin.business.usecase.list.sort.Page
import cz.tyckouni.mvpgate.admin.business.usecase.list.sort.SepSort
import cz.tyckouni.mvpgate.entity.Sep
import cz.tyckouni.mvpgate.party.database.repository.SepRepository
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Component

/**
 * Jpa implementation of the [SepFindJpa] gateway
 */
@Component
class SepFindJpa(
    private val sepRepository: SepRepository,
) : cz.tyckouni.mvpgate.admin.business.gateway.storage.sep.SepFind {
    override fun find(pageRequest: cz.tyckouni.mvpgate.admin.business.request.PageRequest<SepSort>): Page<Sep> {
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
