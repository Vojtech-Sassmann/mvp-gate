package cz.tyckouni.mvpgate.party.graphql.presenter

import cz.tyckouni.mvpgate.admin.business.usecase.list.sort.Page
import cz.tyckouni.mvpgate.entity.Sep
import cz.tyckouni.mvpgate.party.graphql.dto.SepConnection
import org.springframework.stereotype.Component

/**
 * Presenter that transforms [Page] to [Sep] to [SepConnection]
 */
@Component
class SepConnectionGraphQLPresenter(
    private val sepGraphQLPresenter: SepGraphQLPresenter,
) {
    fun present(sepPage: Page<Sep>): SepConnection {
        val seps = sepPage.elements
            .map(sepGraphQLPresenter::present)

        return SepConnection(
            totalCount = sepPage.totalSize,
            nodes = seps,
        )
    }
}
