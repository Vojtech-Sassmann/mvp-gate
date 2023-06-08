package cz.tyckouni.mvpgate.party.application

import cz.tyckouni.mvpgate.party.business.gateway.GuidProvider
import cz.tyckouni.mvpgate.party.business.gateway.Idps
import cz.tyckouni.mvpgate.party.business.gateway.Seps
import cz.tyckouni.mvpgate.party.business.usecase.create.CreateIdpInteractor
import cz.tyckouni.mvpgate.party.business.usecase.create.CreateIdpUseCase
import cz.tyckouni.mvpgate.party.business.usecase.create.CreateSepInteractor
import cz.tyckouni.mvpgate.party.business.usecase.create.CreateSepUseCase
import cz.tyckouni.mvpgate.party.business.usecase.list.idp.ListIdpsInteractor
import cz.tyckouni.mvpgate.party.business.usecase.list.idp.ListIdpsUseCase
import cz.tyckouni.mvpgate.party.database.DatabaseConfiguration
import cz.tyckouni.mvpgate.party.database.JpaIdps
import cz.tyckouni.mvpgate.party.database.JpaSeps
import cz.tyckouni.mvpgate.party.database.repository.IdpJpaRepository
import cz.tyckouni.mvpgate.party.database.repository.SepJpaRepository
import cz.tyckouni.mvpgate.party.graphql.GraphQLConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
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
class AdminConfiguration {

    @Bean
    fun idps(idpJpaRepository: IdpJpaRepository): Idps = JpaIdps(idpJpaRepository)

    @Bean
    fun seps(sepJpaRepository: SepJpaRepository): Seps = JpaSeps(sepJpaRepository)

    @Bean
    fun listIdpsUseCase(idps: Idps): ListIdpsUseCase = ListIdpsInteractor(idps)

    @Bean
    fun guidProvider(): GuidProvider = GuidProvider { UUID.randomUUID().toString() }

    @Bean
    fun createIdpUseCase(idps: Idps, guidProvider: GuidProvider): CreateIdpUseCase =
        CreateIdpInteractor(idps, guidProvider)

    @Bean
    fun corsConfigurer(): WebMvcConfigurer = object : WebMvcConfigurer {
        override fun addCorsMappings(registry: CorsRegistry) {
            registry
                .addMapping("/graphql")
                .allowedOrigins("http://localhost:4200")
        }
    }

    @Bean
    fun createSepUseCase(seps: Seps, guidProvider: GuidProvider): CreateSepUseCase =
        CreateSepInteractor(seps, guidProvider)
}
