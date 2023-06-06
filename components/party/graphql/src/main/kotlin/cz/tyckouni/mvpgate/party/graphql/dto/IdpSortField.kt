package cz.tyckouni.mvpgate.party.graphql.dto

import cz.tyckouni.mvpgate.party.business.usecase.list.idp.IdpSort

/**
 * Graphql representation of possible field which can be used to sort Idps
 */
@Suppress("unused") // used by graphql
enum class IdpSortField(
    val idpSort: IdpSort,
) {
    NAME(IdpSort.NAME),
}
