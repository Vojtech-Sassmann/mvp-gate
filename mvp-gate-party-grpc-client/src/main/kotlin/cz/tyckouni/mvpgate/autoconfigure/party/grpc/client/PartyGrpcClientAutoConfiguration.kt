package cz.tyckouni.mvpgate.autoconfigure.party.grpc.client

import brave.Tracing
import brave.grpc.GrpcTracing
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
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
    ): ManagedChannel {
        val channelBuilder = ManagedChannelBuilder
            .forAddress(partyHost, partyPort)
            .usePlaintext()

        if (tracing != null) {
            channelBuilder.intercept(GrpcTracing.create(tracing).newClientInterceptor())
        }

        return channelBuilder.build()
    }
}
