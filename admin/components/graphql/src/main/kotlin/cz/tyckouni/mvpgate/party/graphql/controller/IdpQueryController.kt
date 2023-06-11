package cz.tyckouni.mvpgate.party.graphql.controller

import cz.tyckouni.mvpgate.entity.Idp
import cz.tyckouni.mvpgate.party.business.usecase.list.ListUseCase
import cz.tyckouni.mvpgate.party.business.usecase.list.PageRequest
import cz.tyckouni.mvpgate.party.business.usecase.list.idp.IdpSort
import cz.tyckouni.mvpgate.party.graphql.dto.IdpConnection
import cz.tyckouni.mvpgate.party.graphql.dto.IdpOrder
import cz.tyckouni.mvpgate.party.graphql.presenter.IdpConnectionGraphQLPresenter
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

/**
 * Graphql controller for Idp related queries
 */
@Controller
class IdpQueryController(
    private val listIdpsUseCase: ListUseCase<Idp, IdpSort>,
    private val idpConnectionGraphQLPresenter: IdpConnectionGraphQLPresenter,
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

        return idpConnectionGraphQLPresenter.present(idpPage)
    }
}
