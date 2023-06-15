package cz.tyckouni.mvpgate.party.graphql.presenter

import cz.tyckouni.mvpgate.entity.Sep
import cz.tyckouni.mvpgate.party.graphql.dto.sep.SepDto
import org.springframework.stereotype.Component

/**
 * Presenter that transforms [Sep] into [SepDto]
 */
@Component
class SepGraphQLPresenter {

    fun present(sep: Sep): SepDto = SepDto(
        id = sep.getGuid(),
        name = sep.getName(),
        redirectUrls = sep.getRedirectUrls(),
    )
}
