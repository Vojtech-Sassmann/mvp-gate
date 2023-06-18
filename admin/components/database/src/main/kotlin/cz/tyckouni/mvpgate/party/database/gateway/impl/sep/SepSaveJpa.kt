package cz.tyckouni.mvpgate.party.database.gateway.impl.sep

import cz.tyckouni.mvpgate.admin.business.gateway.storage.sep.SepSave
import cz.tyckouni.mvpgate.entity.Sep
import cz.tyckouni.mvpgate.party.database.entity.SepJpa
import cz.tyckouni.mvpgate.party.database.repository.SepRepository
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Component
import java.util.UUID

/**
 * Jpa implementation of the [SepSave] gateway
 */
@Component
class SepSaveJpa(
    private val sepRepository: SepRepository,
) : cz.tyckouni.mvpgate.admin.business.gateway.storage.sep.SepSave {
    override fun save(sep: Sep) {
        val sepJpa = SepJpa(
            guid = sep.getGuid(),
            name = sep.getName(),
            redirectUrls = sep.getRedirectUrls(),
        )
        sepRepository.save(sepJpa)
    }

    @PostConstruct
    fun init() {
        sepRepository.saveAll(
            listOf(
                SepJpa(
                    guid = UUID.randomUUID().toString(),
                    name = "sep1",
                ),
                SepJpa(
                    guid = UUID.randomUUID().toString(),
                    name = "sep2",
                ),
                SepJpa(
                    guid = UUID.randomUUID().toString(),
                    name = "sep3",
                ),
            ),
        )
    }
}
