package cz.tyckouni.mvpgate.party.business.core.impl

import cz.tyckouni.mvpgate.party.business.core.ServiceBase
import cz.tyckouni.mvpgate.party.business.core.SpService
import cz.tyckouni.mvpgate.party.persistence.entity.Sp
import cz.tyckouni.mvpgate.party.persistence.repository.SpRepository
import org.springframework.stereotype.Service
import java.util.UUID

/**
 * Business service for [Sp] entity management
 */
@Service
class SpServiceImpl(
    private val spRepository: SpRepository,
) : ServiceBase<Sp>(spRepository), SpService {

    override fun findByName(name: String): Set<Sp> {
        return spRepository.findByName(name)
    }

    override fun create(name: String): Sp {
        return spRepository.save(Sp(name))
    }

    override fun delete(guid: UUID) {
        spRepository.delete(findByGuid(guid).get())
    }
}
