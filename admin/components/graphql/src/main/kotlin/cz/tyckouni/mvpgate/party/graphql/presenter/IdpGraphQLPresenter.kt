package cz.tyckouni.mvpgate.party.graphql.presenter

import cz.tyckouni.mvpgate.entity.Idp
import cz.tyckouni.mvpgate.party.graphql.dto.idp.IdpGraphQL
import org.springframework.stereotype.Component

/**
 * Presenter that transforms [Idp] into [IdpGraphQL]
 */
@Component
class IdpGraphQLPresenter {

    fun present(idp: Idp): IdpGraphQL = IdpGraphQL(
        name = idp.getName(),
        id = idp.getGuid(),
        loginUrl = idp.getLoginUrl(),
    )
}
