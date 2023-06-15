package cz.tyckouni.mvpgate.party.graphql.dto

/**
 * Graphql input type representing possible Sep sorting
 */
class SepOrder(
    val field: SepSortField,
    val direction: Direction,
)
