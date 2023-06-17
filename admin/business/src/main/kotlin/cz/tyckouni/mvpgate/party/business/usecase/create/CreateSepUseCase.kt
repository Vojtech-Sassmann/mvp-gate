package cz.tyckouni.mvpgate.party.business.usecase.create

import cz.tyckouni.mvpgate.entity.Sep
import cz.tyckouni.mvpgate.party.business.request.CreateSepRequest
import cz.tyckouni.mvpgate.party.business.usecase.validation.ValidationException

fun interface CreateSepUseCase {

    /**
     * Creates new [Sep] and persists it
     *
     * The new [Sep] must meet these conditions:
     * - unique name
     * - redirectUrls must not be empty
     * - redirectUrls must contain valid urls
     *
     * @throws ValidationException if some of the conditions is not met
     * @return created [Sep]
     */
    fun create(createSepRequest: CreateSepRequest): Sep
}
