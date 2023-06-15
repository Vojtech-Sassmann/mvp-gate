package cz.tyckouni.mvpgate.party.database.gateway.converter

/**
 * Converter from JPA entities [D] to application entities [E].
 *
 * @param D jpa entity type
 * @param E application entity type
 */
fun interface JpaConverter<D, E> {

    /**
     * Converts entity.
     *
     * Converts given JPA entity [D] into application entity [E]
     *
     * @param dbEntity JPA entity to be converted
     * @return converted entity [E]
     */
    fun convert(dbEntity: D): E
}
