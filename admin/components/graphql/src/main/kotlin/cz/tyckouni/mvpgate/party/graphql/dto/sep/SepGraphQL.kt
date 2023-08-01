package cz.tyckouni.mvpgate.party.graphql.dto.sep

class SepGraphQL(
    val id: String,
    val name: String,
    val redirectUrls: Set<String>,
)
