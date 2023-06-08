package cz.tyckouni.mvpgate.party.database

import cz.tyckouni.mvpgate.entity.Sep
import cz.tyckouni.mvpgate.party.business.gateway.Seps
import cz.tyckouni.mvpgate.party.database.entity.SepJpa
import cz.tyckouni.mvpgate.party.database.repository.SepJpaRepository

class JpaSeps(
    private val repository: SepJpaRepository,
) : Seps {
    override fun save(sep: Sep) {
        val sepJpa = SepJpa(
            guid = sep.getGuid(),
            name = sep.getName(),
            redirectUrls = sep.getRedirectUrls(),
        )
        repository.save(sepJpa)
    }

    override fun existsByName(name: String): Boolean = repository.existsByName(name)
}
