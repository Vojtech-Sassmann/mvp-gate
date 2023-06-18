package cz.tyckouni.mvpgate.party.graphql.dto

import cz.tyckouni.mvpgate.admin.business.usecase.list.sort.Order

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
