package cz.tyckouni.mvpgate.party.database.gateway.impl.sep

import cz.tyckouni.mvpgate.admin.business.gateway.storage.sep.SepExistsByName
import cz.tyckouni.mvpgate.party.database.repository.SepRepository
import org.springframework.stereotype.Component

/**
 * Jpa implementation of the [SepExistsByName]
 */
@Component
class SepExistsByNameJpa(
    private val sepRepository: SepRepository,
) : cz.tyckouni.mvpgate.admin.business.gateway.storage.sep.SepExistsByName {
    override fun existsByName(name: String): Boolean = sepRepository.existsByName(name)
}
