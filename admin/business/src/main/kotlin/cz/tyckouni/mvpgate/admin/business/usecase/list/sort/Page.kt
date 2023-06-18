package cz.tyckouni.mvpgate.admin.business.usecase.list.sort

/**
 * Page of entities
 */
class Page<T>(
    val elements: List<T>,
    val totalSize: Long,
)
