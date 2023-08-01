package cz.tyckouni.mvpgate.party.graphql.controller.query

import cz.tyckouni.mvpgate.admin.business.input.PageInput
import cz.tyckouni.mvpgate.admin.business.usecase.list.ListUseCase
import cz.tyckouni.mvpgate.admin.business.usecase.list.sort.SepSort
import cz.tyckouni.mvpgate.entity.Sep
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
class SepsQueryController(
    private val listSepsUseCase: ListUseCase<Sep, SepSort>,
    private val sepConnectionGraphQLPresenter: SepConnectionGraphQLPresenter,
) {

    @QueryMapping
    fun seps(@Argument size: Int, @Argument page: Int, @Argument orderBy: SepOrder): SepConnection {
        val idpPage = listSepsUseCase.list(
            PageInput(
                page = page,
                size = size,
                sortProperty = orderBy.field.sepSort,
                order = orderBy.direction.order,
            ),
        )

        return sepConnectionGraphQLPresenter.present(idpPage)
    }
}
