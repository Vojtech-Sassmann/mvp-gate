package cz.tyckouni.mvpgate.admin.business.usecase.create

import cz.tyckouni.mvpgate.admin.business.input.CreateSepInput
import cz.tyckouni.mvpgate.admin.business.usecase.validation.ValidationException
import cz.tyckouni.mvpgate.entity.Sep

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
    fun create(createSepInput: CreateSepInput): Sep
}
