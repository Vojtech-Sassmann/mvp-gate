package cz.tyckouni.mvpgate.party.database.gateway.converter.impl

import cz.tyckouni.mvpgate.entity.Idp
import cz.tyckouni.mvpgate.entity.IdpFactory
import cz.tyckouni.mvpgate.party.database.entity.IdpJpa
import cz.tyckouni.mvpgate.party.database.gateway.converter.JpaConverter
import org.springframework.stereotype.Component

/**
 * Idp implementation of the [JpaConverter]
 */
@Component
class IdpJpaConverter : JpaConverter<IdpJpa, Idp> {
    override fun convert(dbEntity: IdpJpa): Idp = IdpFactory.create(
        guid = dbEntity.guid,
        name = dbEntity.name,
        loginUrl = dbEntity.loginUrl,
    )
}
