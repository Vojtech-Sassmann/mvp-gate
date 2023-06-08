package cz.tyckouni.mvpgate.party.graphql.presenter

import cz.tyckouni.mvpgate.entity.Idp
import cz.tyckouni.mvpgate.party.graphql.dto.idp.IdpDto
import org.springframework.stereotype.Component

/**
 * Presenter that transforms [Idp] into [IdpDto]
 */
@Component
class IdpGraphQLPresenter {

    fun present(idp: Idp): IdpDto = IdpDto(
        name = idp.getName(),
        id = idp.getGuid(),
        loginUrl = idp.getLoginUrl(),
    )
}
