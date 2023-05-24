package cz.tyckouni.mvpgate.party.application.graphql.controller

import cz.tyckouni.mvpgate.party.application.graphql.dto.IdpConnection
import cz.tyckouni.mvpgate.party.application.graphql.dto.IdpOrder
import cz.tyckouni.mvpgate.party.application.graphql.presenter.IdpConnectionPresenter
import cz.tyckouni.mvpgate.party.business.usecase.list.PageRequest
import cz.tyckouni.mvpgate.party.business.usecase.list.idp.ListIdpsUseCase
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

/**
 * Graphql controller for Idp related queries
 */
@Controller
class IdpController(
    private val listIdpsUseCase: ListIdpsUseCase,
    private val idpConnectionPresenter: IdpConnectionPresenter,
) {

    @QueryMapping
    fun idps(@Argument size: Int, @Argument page: Int, @Argument orderBy: IdpOrder): IdpConnection {
        val idpPage = listIdpsUseCase.list(
            PageRequest(
                page = page,
                size = size,
                sortProperty = orderBy.field.idpSort,
                order = orderBy.direction.order,
            ),
        )

        return idpConnectionPresenter.present(idpPage)
    }
}
