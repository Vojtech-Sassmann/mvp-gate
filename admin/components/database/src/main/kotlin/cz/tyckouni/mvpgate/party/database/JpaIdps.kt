package cz.tyckouni.mvpgate.party.database

import cz.tyckouni.mvpgate.entity.CommonIdp
import cz.tyckouni.mvpgate.entity.Idp
import cz.tyckouni.mvpgate.party.business.gateway.Idps
import cz.tyckouni.mvpgate.party.business.usecase.list.Order
import cz.tyckouni.mvpgate.party.business.usecase.list.Page
import cz.tyckouni.mvpgate.party.business.usecase.list.PageRequest
import cz.tyckouni.mvpgate.party.business.usecase.list.idp.IdpSort
import cz.tyckouni.mvpgate.party.database.entity.IdpJpa
import cz.tyckouni.mvpgate.party.database.repository.IdpJpaRepository
import jakarta.annotation.PostConstruct
import org.springframework.data.domain.Sort
import java.util.UUID

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
            pageRequest.page,
            pageRequest.size,
            getSortDirection(pageRequest.order),
            getSortProperty(pageRequest.sortProperty),
        )

        val jpaPage = repository.findAll(pageable)
            .map { idp -> convertToIdp(idp) }

        return Page(jpaPage.content, jpaPage.totalElements)
    }

    override fun existsByName(name: String): Boolean {
        return repository.existsByName(name)
    }

    private fun convertToIdp(idp: IdpJpa): Idp = CommonIdp(idp.guid, idp.name, idp.loginUrl)

    private fun getSortDirection(order: Order): Sort.Direction = when (order) {
        Order.ASCENDING -> Sort.Direction.ASC
        Order.DESCENDING -> Sort.Direction.DESC
    }

    private fun getSortProperty(idpSort: IdpSort): String = when (idpSort) {
        IdpSort.NAME -> "name"
    }

    @PostConstruct
    fun init() {
        repository.saveAll(
            listOf(
                IdpJpa(
                    guid = UUID.randomUUID().toString(),
                    name = "facebook",
                    loginUrl = "https://login",
                ),
                IdpJpa(
                    guid = UUID.randomUUID().toString(),
                    name = "google",
                    loginUrl = "https://login",
                ),
                IdpJpa(
                    guid = UUID.randomUUID().toString(),
                    name = "linkedin",
                    loginUrl = "https://login",
                ),
                IdpJpa(
                    guid = UUID.randomUUID().toString(),
                    name = "myid",
                    loginUrl = "https://login",
                ),
                IdpJpa(
                    guid = UUID.randomUUID().toString(),
                    name = "yourid",
                    loginUrl = "https://login",
                ),
                IdpJpa(
                    guid = UUID.randomUUID().toString(),
                    name = "grandpaid",
                    loginUrl = "https://login",
                ),
                IdpJpa(
                    guid = UUID.randomUUID().toString(),
                    name = "motherid",
                    loginUrl = "https://login",
                ),
                IdpJpa(
                    guid = UUID.randomUUID().toString(),
                    name = "orangeid",
                    loginUrl = "https://login",
                ),
                IdpJpa(
                    guid = UUID.randomUUID().toString(),
                    name = "fruitid",
                    loginUrl = "https://login",
                ),
                IdpJpa(
                    guid = UUID.randomUUID().toString(),
                    name = "begoid",
                    loginUrl = "https://login",
                ),
            ),
        )
    }
}
