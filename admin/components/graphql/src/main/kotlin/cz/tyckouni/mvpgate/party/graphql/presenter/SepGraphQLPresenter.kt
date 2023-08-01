package cz.tyckouni.mvpgate.party.graphql.presenter

import cz.tyckouni.mvpgate.entity.Sep
import cz.tyckouni.mvpgate.party.graphql.dto.sep.SepGraphQL
import org.springframework.stereotype.Component

/**
 * Presenter that transforms [Sep] into [SepGraphQL]
 */
@Component
class SepGraphQLPresenter {

    fun present(sep: Sep): SepGraphQL = SepGraphQL(
        id = sep.getGuid(),
        name = sep.getName(),
        redirectUrls = sep.getRedirectUrls(),
    )
}
