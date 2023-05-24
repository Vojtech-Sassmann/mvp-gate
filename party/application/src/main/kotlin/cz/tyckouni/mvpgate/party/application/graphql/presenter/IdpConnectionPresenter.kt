package cz.tyckouni.mvpgate.party.application.graphql.presenter

import cz.tyckouni.mvpgate.party.application.graphql.dto.IdpConnection
import cz.tyckouni.mvpgate.party.business.entity.Idp
import cz.tyckouni.mvpgate.party.business.usecase.list.Page
import org.springframework.stereotype.Component

/**
 * Presenter that transforms [Page] of [Idp] to [IdpConnection]
 */
@Component
class IdpConnectionPresenter(
    private val idpPresenter: IdpPresenter,
) {

    fun present(idpPage: Page<Idp>): IdpConnection {
        val idps = idpPage.elements
            .map(idpPresenter::present)

        return IdpConnection(
            totalCount = idpPage.totalSize,
            nodes = idps,
        )
    }
}
