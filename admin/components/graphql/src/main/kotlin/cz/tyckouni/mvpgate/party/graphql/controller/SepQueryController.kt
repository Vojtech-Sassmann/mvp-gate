package cz.tyckouni.mvpgate.party.graphql.controller

import cz.tyckouni.mvpgate.entity.Sep
import cz.tyckouni.mvpgate.party.business.usecase.list.ListUseCase
import cz.tyckouni.mvpgate.party.business.usecase.list.PageRequest
import cz.tyckouni.mvpgate.party.business.usecase.list.sep.SepSort
import cz.tyckouni.mvpgate.party.graphql.dto.SepConnection
import cz.tyckouni.mvpgate.party.graphql.dto.SepOrder
import cz.tyckouni.mvpgate.party.graphql.presenter.SepConnectionGraphQLPresenter
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

/**
 * Graphql controller for Sep related queries
 */
@Controller
class SepQueryController(
    private val listSepsUseCase: ListUseCase<Sep, SepSort>,
    private val sepConnectionGraphQLPresenter: SepConnectionGraphQLPresenter,
) {

    @QueryMapping
    fun seps(@Argument size: Int, @Argument page: Int, @Argument orderBy: SepOrder): SepConnection {
        val idpPage = listSepsUseCase.list(
            PageRequest(
                page = page,
                size = size,
                sortProperty = orderBy.field.sepSort,
                order = orderBy.direction.order,
            ),
        )

        return sepConnectionGraphQLPresenter.present(idpPage)
    }
}