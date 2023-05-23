package cz.tyckouni.mvpgate.party.business.usecase.list

class PageRequest(
    val number: Int,
    val size: Int,
    val sortField: String,
    val order: Order,
)
