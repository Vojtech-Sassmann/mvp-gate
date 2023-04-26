package cz.tyckouni.mvpgate.auth.controller

import cz.tyckouni.mvpgate.grpc.ByGuidRequest
import cz.tyckouni.mvpgate.party.grpc.IdpServiceGrpc
import io.grpc.ManagedChannel
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
class LoginController(
    val partManagedChannel: ManagedChannel,
) {

    @GetMapping("/login/hello")
    fun hello(): String {
        LOGGER.debug("Hello")

        IdpServiceGrpc.newBlockingStub(partManagedChannel)
            .findByGuid(
                ByGuidRequest.newBuilder()
                    .setGuid(UUID.randomUUID().toString())
                    .build(),
            )

        return "hello"
    }

    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(LoginController::class.java)
    }
}
