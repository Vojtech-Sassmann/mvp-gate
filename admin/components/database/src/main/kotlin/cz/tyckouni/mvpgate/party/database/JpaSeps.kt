package cz.tyckouni.mvpgate.party.database

import cz.tyckouni.mvpgate.entity.Sep
import cz.tyckouni.mvpgate.entity.SepFactory
import cz.tyckouni.mvpgate.party.business.gateway.Seps
import cz.tyckouni.mvpgate.party.business.usecase.list.Order
import cz.tyckouni.mvpgate.party.business.usecase.list.Page
import cz.tyckouni.mvpgate.party.business.usecase.list.PageRequest
import cz.tyckouni.mvpgate.party.business.usecase.list.sep.SepSort
import cz.tyckouni.mvpgate.party.database.entity.SepJpa
import cz.tyckouni.mvpgate.party.database.repository.SepJpaRepository
import jakarta.annotation.PostConstruct
import org.springframework.data.domain.Sort
import java.util.UUID

class JpaSeps(
    private val repository: SepJpaRepository,
) : Seps {
    override fun save(sep: Sep) {
        val sepJpa = SepJpa(
            guid = sep.getGuid(),
            name = sep.getName(),
            redirectUrls = sep.getRedirectUrls(),
        )
        repository.save(sepJpa)
    }

    override fun find(pageRequest: PageRequest<SepSort>): Page<Sep> {
        val pageable = org.springframework.data.domain.PageRequest.of(
            pageRequest.page,
            pageRequest.size,
            getSortDirection(pageRequest.order),
            getSortProperty(pageRequest.sortProperty),
        )

        val jpaPage = repository.findAll(pageable)
            .map { sep -> convertToSep(sep) }

        return Page(jpaPage.content, jpaPage.totalElements)
    }

    private fun getSortDirection(order: Order): Sort.Direction = when (order) {
        Order.ASCENDING -> Sort.Direction.ASC
        Order.DESCENDING -> Sort.Direction.DESC
    }

    private fun getSortProperty(sepSort: SepSort): String = when (sepSort) {
        SepSort.NAME -> "name"
    }

    private fun convertToSep(sep: SepJpa): Sep = SepFactory.create(sep.guid, sep.name, sep.redirectUrls)

    override fun existsByName(name: String): Boolean = repository.existsByName(name)

    @PostConstruct
    fun init() {
        repository.saveAll(
            listOf(
                SepJpa(
                    guid = UUID.randomUUID().toString(),
                    name = "sep1",
                ),
                SepJpa(
                    guid = UUID.randomUUID().toString(),
                    name = "sep2",
                ),
                SepJpa(
                    guid = UUID.randomUUID().toString(),
                    name = "sep3",
                ),
            )
        )
    }
}
