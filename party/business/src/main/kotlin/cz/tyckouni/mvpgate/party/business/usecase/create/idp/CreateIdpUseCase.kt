package cz.tyckouni.mvpgate.party.business.usecase.create.idp

import cz.tyckouni.mvpgate.party.business.entity.Idp

interface CreateIdpUseCase {

    /**
     * Creates new [Idp] from given [createIdpInput]
     *
     * @return created [Idp] with [Idp.guid] set
     */
    fun create(createIdpInput: CreateIdpInput): Idp
}
