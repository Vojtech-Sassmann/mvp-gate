package cz.tyckouni.mvpgate.party.business.usecase.create

import cz.tyckouni.mvpgate.entity.Idp
import cz.tyckouni.mvpgate.party.business.request.CreateIdpRequest
import cz.tyckouni.mvpgate.party.business.usecase.validation.ValidationException

fun interface CreateIdpUseCase {

    /**
     * Creates new [Idp] from given [createIdpRequest]
     *
     * The new idp must have:
     * - unique name
     * - a valid login url
     *
     * @throws ValidationException if some of the validation conditions is not met
     * @return created [Idp] with guid set
     */
    fun create(createIdpRequest: CreateIdpRequest): Idp
}
