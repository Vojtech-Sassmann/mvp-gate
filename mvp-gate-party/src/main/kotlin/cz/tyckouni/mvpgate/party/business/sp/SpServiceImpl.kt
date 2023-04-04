package cz.tyckouni.mvpgate.party.business.sp

import cz.tyckouni.mvpgate.party.business.ServiceBase
import cz.tyckouni.mvpgate.party.persistence.entity.Sp
import cz.tyckouni.mvpgate.party.persistence.repository.SpRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class SpServiceImpl(
    private val spRepository: SpRepository,
) : ServiceBase<Sp>(spRepository), SpService {

    override fun findByName(name: String): Set<Sp> {
        return spRepository.findByName(Objects.requireNonNull(name, "name cannot be null"))
    }

    override fun create(name: String): Sp {
        return spRepository.save(Sp(name))
    }

    override fun delete(guid: UUID) {
        spRepository.delete(findByGuid(guid).get())
    }
}
