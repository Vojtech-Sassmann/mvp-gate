package cz.tyckouni.mvpgate.party.grpc.interceptor

import brave.Tracing
import brave.grpc.GrpcTracing
import io.grpc.ServerInterceptor
import org.lognet.springboot.grpc.GRpcGlobalInterceptor
import org.springframework.boot.actuate.autoconfigure.tracing.ConditionalOnEnabledTracing
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class GrpcInterceptorConfiguration {

    @Bean
    @GRpcGlobalInterceptor
    @ConditionalOnEnabledTracing
    fun tracingInterceptor(tracing: Tracing): ServerInterceptor {
        return GrpcTracing.create(tracing).newServerInterceptor()
    }
}
