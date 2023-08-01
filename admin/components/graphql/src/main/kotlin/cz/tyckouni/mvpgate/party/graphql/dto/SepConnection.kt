package cz.tyckouni.mvpgate.party.graphql.dto

import cz.tyckouni.mvpgate.party.graphql.dto.sep.SepGraphQL

/**
 * GraphQL DTO for SepConnection
 */
data class SepConnection(
    val totalCount: Long,
    val nodes: List<SepGraphQL>,
)
