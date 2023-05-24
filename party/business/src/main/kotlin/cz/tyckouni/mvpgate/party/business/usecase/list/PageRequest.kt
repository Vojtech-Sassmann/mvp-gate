package cz.tyckouni.mvpgate.party.business.usecase.list

/**
 * Representation of a page request
 */
class PageRequest<T : Sort>(
    val page: Int,
    val size: Int,
    val sortProperty: T,
    val order: Order,
)
