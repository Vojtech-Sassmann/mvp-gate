package cz.tyckouni.mvpgate.party.graphql.dto.sep

data class CreateSepGraphQLInput(
    val name: String,
    val redirectUrls: Set<String>,
)
