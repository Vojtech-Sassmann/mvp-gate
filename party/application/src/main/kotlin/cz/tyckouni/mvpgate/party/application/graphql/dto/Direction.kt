package cz.tyckouni.mvpgate.party.application.graphql.dto

import cz.tyckouni.mvpgate.party.business.usecase.list.Order

/**
 * Direction of the order in GraphQL
 */
@Suppress("unused") // used by graphql
enum class Direction(
    val order: Order,
) {
    ASC(Order.ASCENDING),
    DESC(Order.DESCENDING),
}
