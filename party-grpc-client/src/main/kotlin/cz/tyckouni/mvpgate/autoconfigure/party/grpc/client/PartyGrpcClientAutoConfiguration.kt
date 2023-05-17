package cz.tyckouni.mvpgate.autoconfigure.party.grpc.client

import brave.Tracing
import cz.tyckouni.mvpgate.telemetry.ManagedChannelFactory
import io.grpc.ManagedChannel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.context.annotation.Bean

@AutoConfiguration
class PartyGrpcClientAutoConfiguration {

    @Bean
    fun partyManagedChannel(
        @Autowired(required = false) tracing: Tracing?,
        @Value("\${mvp.party.grpc.host}") partyHost: String,
        @Value("\${mvp.party.grpc.port}") partyPort: Int,
    ): ManagedChannel = ManagedChannelFactory.build(partyHost, partyPort, tracing)
}
