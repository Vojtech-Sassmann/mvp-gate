package cz.tyckouni.mvpgate.party.business.usecase.list

class PageRequest<T : Sort>(
    val number: Int,
    val size: Int,
    val sortProperty: T,
    val order: Order,
)
