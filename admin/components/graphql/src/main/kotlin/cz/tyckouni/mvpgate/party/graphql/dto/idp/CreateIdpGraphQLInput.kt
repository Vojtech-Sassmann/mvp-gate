package cz.tyckouni.mvpgate.party.graphql.dto.idp

data class CreateIdpGraphQLInput(
    val name: String,
    val loginUrl: String,
)
