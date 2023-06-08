package cz.tyckouni.mvpgate.party.graphql.controller

import cz.tyckouni.mvpgate.party.business.usecase.create.CreateSepRequest
import cz.tyckouni.mvpgate.party.business.usecase.create.CreateSepUseCase
import cz.tyckouni.mvpgate.party.graphql.dto.sep.CreateSepInput
import cz.tyckouni.mvpgate.party.graphql.dto.sep.SepDto
import cz.tyckouni.mvpgate.party.graphql.presenter.SepGraphQLPresenter
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.stereotype.Controller

@Controller
class SepMutationController(
    val sepGraphQLPresenter: SepGraphQLPresenter,
    val createSepUseCase: CreateSepUseCase,
) {
    @MutationMapping
    fun createSep(@Argument createSepInput: CreateSepInput): SepDto {
        val createSepRequest = CreateSepRequest(createSepInput.name, createSepInput.redirectUrls)
        val createdSep = createSepUseCase.create(createSepRequest)

        return sepGraphQLPresenter.present(createdSep)
    }
}
