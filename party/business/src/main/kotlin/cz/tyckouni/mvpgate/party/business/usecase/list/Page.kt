package cz.tyckouni.mvpgate.party.business.usecase.list

class Page<T>(
    val elements: List<T>,
    val totalSize: Int,
)
