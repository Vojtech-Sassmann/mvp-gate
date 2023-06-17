package cz.tyckouni.mvpgate.party.business.request

import cz.tyckouni.mvpgate.party.business.usecase.list.sort.Order
import cz.tyckouni.mvpgate.party.business.usecase.list.sort.Sort

/**
 * Representation of a page request
 */
class PageRequest<T : Sort<*>>(
    val page: Int,
    val size: Int,
    val sortProperty: T,
    val order: Order,
)
