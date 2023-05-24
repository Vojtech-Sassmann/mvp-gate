package cz.tyckouni.mvpgate.party.graphql.controller

import cz.tyckouni.mvpgate.party.business.usecase.create.idp.CreateIdpRequest
import cz.tyckouni.mvpgate.party.business.usecase.create.idp.CreateIdpUseCase
import cz.tyckouni.mvpgate.party.graphql.dto.idp.CreateIdpInput
import cz.tyckouni.mvpgate.party.graphql.dto.idp.IdpDto
import cz.tyckouni.mvpgate.party.graphql.presenter.IdpPresenter
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.stereotype.Controller

/**
 * GraphQL controller handling idp-related mutations.
 */
@Controller
class IdpMutationController(
    val idpPresenter: IdpPresenter,
    val createIdpUseCase: CreateIdpUseCase,
) {

    @MutationMapping
    fun createIdp(@Argument createIdpInput: CreateIdpInput): IdpDto {
        val createdIdp = createIdpUseCase.create(
            CreateIdpRequest(
                name = createIdpInput.name,
                loginUrl = createIdpInput.loginUrl,
            ),
        )

        return idpPresenter.present(createdIdp)
    }
}
