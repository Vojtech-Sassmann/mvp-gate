package cz.tyckouni.mvpgate.party.application

import cz.tyckouni.mvpgate.party.business.gateway.GuidProvider
import cz.tyckouni.mvpgate.party.business.gateway.Idps
import cz.tyckouni.mvpgate.party.business.usecase.create.idp.CreateIdpInteractor
import cz.tyckouni.mvpgate.party.business.usecase.create.idp.CreateIdpUseCase
import cz.tyckouni.mvpgate.party.business.usecase.list.idp.ListIdpsInteractor
import cz.tyckouni.mvpgate.party.business.usecase.list.idp.ListIdpsUseCase
import cz.tyckouni.mvpgate.party.database.DatabaseConfiguration
import cz.tyckouni.mvpgate.party.database.JpaIdps
import cz.tyckouni.mvpgate.party.database.repository.IdpJpaRepository
import cz.tyckouni.mvpgate.party.graphql.GraphQLConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import java.util.UUID

/**
 * Class with beans configuration for the party application
 */
@Configuration
@Import(
    value = [
        DatabaseConfiguration::class,
        GraphQLConfiguration::class,
    ],
)
class PartyConfiguration {

    @Bean
    fun idps(idpJpaRepository: IdpJpaRepository): Idps {
        return JpaIdps(idpJpaRepository)
    }

    @Bean
    fun listIdpsUseCase(idps: Idps): ListIdpsUseCase {
        return ListIdpsInteractor(idps)
    }

    @Bean
    fun guidProvider(): GuidProvider {
        return GuidProvider { UUID.randomUUID().toString() }
    }

    @Bean
    fun createIdpUseCase(idps: Idps, guidProvider: GuidProvider): CreateIdpUseCase {
        return CreateIdpInteractor(idps, guidProvider)
    }
}
