package cz.tyckouni.mvpgate.party.graphql.dto.sep

class SepDto(
    val guid: String,
    val name: String,
    val redirectUrls: Set<String>,
)
