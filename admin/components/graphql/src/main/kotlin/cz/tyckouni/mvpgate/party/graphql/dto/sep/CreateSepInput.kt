package cz.tyckouni.mvpgate.party.graphql.dto.sep

data class CreateSepInput(
    val name: String,
    val redirectUrls: Set<String>,
)
