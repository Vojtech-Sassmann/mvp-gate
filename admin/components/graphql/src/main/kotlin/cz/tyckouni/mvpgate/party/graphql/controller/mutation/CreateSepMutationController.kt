package cz.tyckouni.mvpgate.party.graphql.controller.mutation

import cz.tyckouni.mvpgate.admin.business.input.CreateSepInput
import cz.tyckouni.mvpgate.admin.business.usecase.create.CreateSepUseCase
import cz.tyckouni.mvpgate.admin.business.usecase.validation.ValidationException
import cz.tyckouni.mvpgate.party.graphql.dto.sep.CreateSepGraphQLInput
import cz.tyckouni.mvpgate.party.graphql.dto.sep.SepGraphQL
import cz.tyckouni.mvpgate.party.graphql.error.GraphQLValidationException
import cz.tyckouni.mvpgate.party.graphql.presenter.SepGraphQLPresenter
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.stereotype.Controller

@Controller
class CreateSepMutationController(
    private val sepGraphQLPresenter: SepGraphQLPresenter,
    private val createSepUseCase: CreateSepUseCase,
) {
    @MutationMapping
    fun createSep(@Argument("createSepInput") createSepGraphQlInput: CreateSepGraphQLInput): SepGraphQL {
        try {
            val createSepInput = CreateSepInput(
                createSepGraphQlInput.name,
                createSepGraphQlInput.redirectUrls,
            )
            val createdSep = createSepUseCase.create(createSepInput)

            return sepGraphQLPresenter.present(createdSep)
        } catch (ex: ValidationException) {
            throw GraphQLValidationException(ex)
        }
    }
}
