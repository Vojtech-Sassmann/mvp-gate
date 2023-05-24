package cz.tyckouni.mvpgate.party.graphql.dto.idp

data class CreateIdpInput(
    val name: String,
    val loginUrl: String,
)
