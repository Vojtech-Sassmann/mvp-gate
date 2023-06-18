package cz.tyckouni.mvpgate.party.graphql.dto

import cz.tyckouni.mvpgate.admin.business.usecase.list.sort.SepSort

/**
 * Graphql representation of possible field which can be used to sort Seps
 */
@Suppress("unused") // used by graphql
enum class SepSortField(
    val sepSort: SepSort,
) {
    NAME(SepSort.NAME),
}
