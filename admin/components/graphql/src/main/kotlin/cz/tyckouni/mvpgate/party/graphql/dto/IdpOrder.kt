package cz.tyckouni.mvpgate.party.graphql.dto

/**
 * Graphql input type representing possible Idp sorting
 */
data class IdpOrder(
    val field: IdpSortField,
    val direction: Direction,
)
