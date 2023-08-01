package cz.tyckouni.mvpgate.party.graphql.dto.idp

/**
 * Graphql DTO for Idp
 */
data class IdpGraphQL(
    val name: String,
    val id: String,
    val loginUrl: String,
)
