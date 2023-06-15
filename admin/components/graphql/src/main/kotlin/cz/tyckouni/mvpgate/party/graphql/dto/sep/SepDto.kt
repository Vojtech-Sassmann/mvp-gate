package cz.tyckouni.mvpgate.party.graphql.dto.sep

class SepDto(
    val id: String,
    val name: String,
    val redirectUrls: Set<String>,
)
