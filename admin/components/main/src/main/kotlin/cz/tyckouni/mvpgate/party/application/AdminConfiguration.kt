package cz.tyckouni.mvpgate.party.application

import cz.tyckouni.mvpgate.party.business.gateway.GuidProvider
import cz.tyckouni.mvpgate.party.database.DatabaseConfiguration
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
    fun guidProvider(): GuidProvider = GuidProvider { UUID.randomUUID().toString() }

    @Bean
    fun corsConfigurer(): WebMvcConfigurer = object : WebMvcConfigurer {
        override fun addCorsMappings(registry: CorsRegistry) {
            registry
                .addMapping("/graphql")
                .allowedOrigins("http://localhost:4200")
        }
    }
}
