package cz.tyckouni.mvpgate.party.graphql.controller

import cz.tyckouni.mvpgate.party.business.usecase.create.CreateIdpRequest
import cz.tyckouni.mvpgate.party.business.usecase.create.CreateIdpUseCase
import cz.tyckouni.mvpgate.party.business.usecase.validation.ValidationException
import cz.tyckouni.mvpgate.party.graphql.dto.idp.CreateIdpInput
import cz.tyckouni.mvpgate.party.graphql.dto.idp.IdpDto
import cz.tyckouni.mvpgate.party.graphql.error.GraphQLValidationException
import cz.tyckouni.mvpgate.party.graphql.presenter.IdpGraphQLPresenter
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.stereotype.Controller

/**
 * GraphQL controller handling idp-related mutations.
 */
@Controller
class IdpMutationController(
    val idpGraphQLPresenter: IdpGraphQLPresenter,
    val createIdpUseCase: CreateIdpUseCase,
) {

    @MutationMapping
    fun createIdp(@Argument createIdpInput: CreateIdpInput): IdpDto {
        try {
            val createdIdp = createIdpUseCase.create(
                CreateIdpRequest(
                    name = createIdpInput.name,
                    loginUrl = createIdpInput.loginUrl,
                ),
            )

            return idpGraphQLPresenter.present(createdIdp)
        } catch (ex: ValidationException) {
            throw GraphQLValidationException(ex)
        }
    }
}
