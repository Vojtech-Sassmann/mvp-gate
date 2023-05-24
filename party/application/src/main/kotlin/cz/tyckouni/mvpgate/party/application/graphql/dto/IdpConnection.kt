package cz.tyckouni.mvpgate.party.application.graphql.dto

import cz.tyckouni.mvpgate.party.application.graphql.dto.idp.IdpDto

/**
 * GraphQL DTO for IdpConnection
 */
data class IdpConnection(
    val totalCount: Long,
    val nodes: List<IdpDto>,
)
