package cz.tyckouni.mvpgate.admin.business.usecase.create

import cz.tyckouni.mvpgate.admin.business.input.CreateIdpInput
import cz.tyckouni.mvpgate.admin.business.usecase.validation.ValidationException
import cz.tyckouni.mvpgate.entity.Idp

fun interface CreateIdpUseCase {

    /**
     * Creates new [Idp] from given [createIdpInput]
     *
     * The new idp must have:
     * - unique name
     * - a valid login url
     *
     * @throws ValidationException if some of the validation conditions is not met
     * @return created [Idp] with guid set
     */
    fun create(createIdpInput: CreateIdpInput): Idp
}
