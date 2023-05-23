package cz.tyckouni.mvpgate.party.business.usecase.create.idp

import cz.tyckouni.mvpgate.party.business.entity.Idp

interface CreateIdpUseCase {

    /**
     * Creates new [Idp] from given [createIdpRequest]
     *
     * @return created [Idp] with guid set
     */
    fun create(createIdpRequest: CreateIdpRequest): Idp
}
