package cz.tyckouni.mvpgate.party.application

import cz.tyckouni.mvpgate.party.business.dao.idp.Idps
import cz.tyckouni.mvpgate.party.business.usecase.list.idp.ListIdpsInteractor
import cz.tyckouni.mvpgate.party.business.usecase.list.idp.ListIdpsUseCase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * Class with beans configuration for the party application
 */
@Configuration
class Configuration {

    @Bean
    fun listIdpsUseCase(idps: Idps): ListIdpsUseCase {
        return ListIdpsInteractor(idps)
    }
}
