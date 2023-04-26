package cz.tyckouni.mvpgate.party.grpc

import cz.tyckouni.mvpgate.grpc.ByGuidRequest
import io.grpc.inprocess.InProcessChannelBuilder
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.lognet.springboot.grpc.autoconfigure.GRpcServerProperties
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.NONE,
    properties = [
        "grpc.enabled=false",
        "grpc.inProcessServerName=testServer",
        "grpc.shutdownGrace=-1",
    ],
)
class IdpServiceGrpcIntegrationTests(
    @Autowired
    val gRpcServerProperties: GRpcServerProperties,
) {

    @Test
    fun `Idp is not found`() {
        val channel = InProcessChannelBuilder
            .forName(gRpcServerProperties.inProcessServerName)
            .usePlaintext()
            .build()

        val idp = IdpServiceGrpc.newBlockingStub(channel)
            .findByGuid(
                ByGuidRequest.newBuilder()
                    .setGuid(NOT_EXISTING_GUID)
                    .build(),
            )

        assertThat(idp.guid).isEmpty()
    }

    companion object {
        const val NOT_EXISTING_GUID = "535ad097-6a26-4669-89ff-8bcd2ceb2607"
    }
}
