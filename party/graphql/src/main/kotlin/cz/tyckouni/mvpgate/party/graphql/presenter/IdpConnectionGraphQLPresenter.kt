package cz.tyckouni.mvpgate.party.graphql.presenter

import cz.tyckouni.mvpgate.party.business.entity.Idp
import cz.tyckouni.mvpgate.party.business.usecase.list.Page
import cz.tyckouni.mvpgate.party.graphql.dto.IdpConnection
import org.springframework.stereotype.Component

/**
 * Presenter that transforms [Page] of [Idp] to [IdpConnection]
 */
@Component
class IdpConnectionGraphQLPresenter(
    private val idpGraphQLPresenter: IdpGraphQLPresenter,
) {

    fun present(idpPage: Page<Idp>): IdpConnection {
        val idps = idpPage.elements
            .map(idpGraphQLPresenter::present)

        return IdpConnection(
            totalCount = idpPage.totalSize,
            nodes = idps,
        )
    }
}
