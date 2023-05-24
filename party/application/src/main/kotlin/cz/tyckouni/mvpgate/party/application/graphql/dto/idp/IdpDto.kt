package cz.tyckouni.mvpgate.party.application.graphql.dto.idp

/**
 * Graphql DTO for Idp
 */
data class IdpDto(
    val name: String,
    val guid: String,
    val loginUrl: String,
)
