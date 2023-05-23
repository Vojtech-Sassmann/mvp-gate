package cz.tyckouni.mvpgate.party.business.usecase.list

class Page<T>(
    val elements: List<T>,
    val number: Int,
    val size: Int,
)
