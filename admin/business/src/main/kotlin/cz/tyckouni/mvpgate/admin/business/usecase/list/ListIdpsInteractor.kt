package cz.tyckouni.mvpgate.admin.business.usecase.list

import cz.tyckouni.mvpgate.admin.business.gateway.storage.idp.IdpFind
import cz.tyckouni.mvpgate.admin.business.input.PageInput
import cz.tyckouni.mvpgate.admin.business.usecase.list.sort.IdpSort
import cz.tyckouni.mvpgate.admin.business.usecase.list.sort.Page
import cz.tyckouni.mvpgate.entity.Idp

/**
 * Interactor of the [ListUseCase]
 */
class ListIdpsInteractor(
    private val idpFind: IdpFind,
) : ListUseCase<Idp, IdpSort> {
    override fun list(pageInput: PageInput<IdpSort>): Page<Idp> {
        return idpFind.find(pageInput)
    }
}
