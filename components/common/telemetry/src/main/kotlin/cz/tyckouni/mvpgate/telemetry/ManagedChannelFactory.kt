package cz.tyckouni.mvpgate.telemetry

import brave.Tracing
import brave.grpc.GrpcTracing
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder

class ManagedChannelFactory {

    companion object {
        /**
         * Builds new [ManagedChannel] with given tracing interceptor.
         *
         * @return created [ManagedChannel]
         */
        fun build(host: String, port: Int, tracing: Tracing?): ManagedChannel {
            val channelBuilder = ManagedChannelBuilder
                .forAddress(host, port)
                .usePlaintext()

            if (tracing != null) {
                channelBuilder.intercept(GrpcTracing.create(tracing).newClientInterceptor())
            }

            return channelBuilder.build()
        }
    }
}
