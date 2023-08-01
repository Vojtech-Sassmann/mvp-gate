package cz.tyckouni.mvpgate.party.graphql.controller.mutation

import cz.tyckouni.mvpgate.admin.business.input.CreateIdpInput
import cz.tyckouni.mvpgate.admin.business.usecase.create.CreateIdpUseCase
import cz.tyckouni.mvpgate.admin.business.usecase.validation.ValidationException
import cz.tyckouni.mvpgate.party.graphql.dto.idp.CreateIdpGraphQLInput
import cz.tyckouni.mvpgate.party.graphql.dto.idp.IdpGraphQL
import cz.tyckouni.mvpgate.party.graphql.error.GraphQLValidationException
import cz.tyckouni.mvpgate.party.graphql.presenter.IdpGraphQLPresenter
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.stereotype.Controller

/**
 * GraphQL controller handling idp-related mutations.
 */
@Controller
class CreateIdpMutationController(
    private val idpGraphQLPresenter: IdpGraphQLPresenter,
    private val createIdpUseCase: CreateIdpUseCase,
) {

    @MutationMapping
    fun createIdp(@Argument createIdpGraphQlInput: CreateIdpGraphQLInput): IdpGraphQL {
        try {
            val createdIdp = createIdpUseCase.create(
                CreateIdpInput(
                    name = createIdpGraphQlInput.name,
                    loginUrl = createIdpGraphQlInput.loginUrl,
                ),
            )

            return idpGraphQLPresenter.present(createdIdp)
        } catch (ex: ValidationException) {
            throw GraphQLValidationException(ex)
        }
    }
}
