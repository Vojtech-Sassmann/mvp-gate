package cz.tyckouni.mvpgate.party.business.usecase.list.sort

/**
 * Page of entities
 */
class Page<T>(
    val elements: List<T>,
    val totalSize: Long,
)
