package cz.tyckouni.mvpgate.admin.business.usecase.list

import cz.tyckouni.mvpgate.admin.business.usecase.list.sort.IdpSort
import cz.tyckouni.mvpgate.admin.business.usecase.list.sort.Page
import cz.tyckouni.mvpgate.entity.Idp

/**
 * Interactor of the [ListUseCase]
 */
class ListIdpsInteractor(
    private val idpFind: cz.tyckouni.mvpgate.admin.business.gateway.storage.idp.IdpFind,
) : ListUseCase<Idp, IdpSort> {
    override fun list(pageRequest: cz.tyckouni.mvpgate.admin.business.request.PageRequest<IdpSort>): Page<Idp> {
        return idpFind.find(pageRequest)
    }
}
