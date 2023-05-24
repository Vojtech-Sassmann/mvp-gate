package cz.tyckouni.mvpgate.party.application.graphql.presenter

import cz.tyckouni.mvpgate.party.application.graphql.dto.idp.IdpDto
import cz.tyckouni.mvpgate.party.business.entity.Idp
import org.springframework.stereotype.Component

/**
 * Presenter that transforms [Idp] into [IdpDto]
 */
@Component
class IdpPresenter {

    fun present(idp: Idp): IdpDto {
        return IdpDto(
            name = idp.getName(),
            guid = idp.getGuid(),
            loginUrl = idp.getLoginUrl(),
        )
    }
}
