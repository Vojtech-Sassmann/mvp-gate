package cz.tyckouni.mvpgate.party.business.core.impl

import cz.tyckouni.mvpgate.party.business.core.ServiceBase
import cz.tyckouni.mvpgate.party.business.core.IdpService
import cz.tyckouni.mvpgate.party.persistence.entity.Idp
import cz.tyckouni.mvpgate.party.persistence.repository.IdpRepository
import org.springframework.stereotype.Service

/**
 * Base implementation of [IdpService]
 */
@Service
class IdpServiceImpl(
    idpRepository: IdpRepository,
) : ServiceBase<Idp>(idpRepository), IdpService
