package cz.tyckouni.mvpgate.party.database.gateway.impl.idp

import cz.tyckouni.mvpgate.admin.business.gateway.storage.idp.IdpSave
import cz.tyckouni.mvpgate.entity.Idp
import cz.tyckouni.mvpgate.party.database.entity.IdpJpa
import cz.tyckouni.mvpgate.party.database.repository.IdpRepository
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Component
import java.util.UUID

/**
 * Jpa implementation of the [IdpSave] gateway
 */
@Component
class IdpSaveJpa(
    private val idpRepository: IdpRepository,
) : cz.tyckouni.mvpgate.admin.business.gateway.storage.idp.IdpSave {
    override fun save(idp: Idp) {
        val idpJpa = IdpJpa(
            guid = idp.getGuid(),
            name = idp.getName(),
            loginUrl = idp.getLoginUrl(),
        )

        idpRepository.save(idpJpa)
    }

    @PostConstruct
    fun init() {
        idpRepository.saveAll(
            listOf(
                IdpJpa(
                    guid = UUID.randomUUID().toString(),
                    name = "facebook",
                    loginUrl = "https://login",
                ),
                IdpJpa(
                    guid = UUID.randomUUID().toString(),
                    name = "google",
                    loginUrl = "https://login",
                ),
                IdpJpa(
                    guid = UUID.randomUUID().toString(),
                    name = "linkedin",
                    loginUrl = "https://login",
                ),
                IdpJpa(
                    guid = UUID.randomUUID().toString(),
                    name = "myid",
                    loginUrl = "https://login",
                ),
                IdpJpa(
                    guid = UUID.randomUUID().toString(),
                    name = "yourid",
                    loginUrl = "https://login",
                ),
                IdpJpa(
                    guid = UUID.randomUUID().toString(),
                    name = "grandpaid",
                    loginUrl = "https://login",
                ),
                IdpJpa(
                    guid = UUID.randomUUID().toString(),
                    name = "motherid",
                    loginUrl = "https://login",
                ),
                IdpJpa(
                    guid = UUID.randomUUID().toString(),
                    name = "orangeid",
                    loginUrl = "https://login",
                ),
                IdpJpa(
                    guid = UUID.randomUUID().toString(),
                    name = "fruitid",
                    loginUrl = "https://login",
                ),
                IdpJpa(
                    guid = UUID.randomUUID().toString(),
                    name = "begoid",
                    loginUrl = "https://login",
                ),
            ),
        )
    }
}
