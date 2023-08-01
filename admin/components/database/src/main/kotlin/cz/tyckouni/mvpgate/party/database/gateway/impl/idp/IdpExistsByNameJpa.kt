package cz.tyckouni.mvpgate.party.database.gateway.impl.idp

import cz.tyckouni.mvpgate.admin.business.gateway.storage.idp.IdpExistsByName
import cz.tyckouni.mvpgate.party.database.repository.IdpRepository
import org.springframework.stereotype.Component

/**
 * Jpa implementation of the [IdpExistsByName] gateway
 */
@Component
class IdpExistsByNameJpa(
    private val idpRepository: IdpRepository,
) : IdpExistsByName {
    override fun existsByName(name: String): Boolean {
        return idpRepository.existsByName(name)
    }
}
