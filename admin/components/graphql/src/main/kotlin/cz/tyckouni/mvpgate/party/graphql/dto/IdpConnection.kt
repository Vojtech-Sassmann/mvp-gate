package cz.tyckouni.mvpgate.party.graphql.dto

import cz.tyckouni.mvpgate.party.graphql.dto.idp.IdpGraphQL

/**
 * GraphQL DTO for IdpConnection
 */
data class IdpConnection(
    val totalCount: Long,
    val nodes: List<IdpGraphQL>,
)
