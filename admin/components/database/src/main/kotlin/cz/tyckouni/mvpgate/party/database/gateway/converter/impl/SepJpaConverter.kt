package cz.tyckouni.mvpgate.party.database.gateway.converter.impl

import cz.tyckouni.mvpgate.entity.Sep
import cz.tyckouni.mvpgate.entity.SepFactory
import cz.tyckouni.mvpgate.party.database.entity.SepJpa
import cz.tyckouni.mvpgate.party.database.gateway.converter.JpaConverter
import org.springframework.stereotype.Component

/**
 * Sep implementation of the [JpaConverter]
 */
@Component
class SepJpaConverter : JpaConverter<SepJpa, Sep> {
    override fun convert(dbEntity: SepJpa): Sep = SepFactory.create(
        guid = dbEntity.guid,
        name = dbEntity.name,
        redirectUrls = dbEntity.redirectUrls,
    )
}
