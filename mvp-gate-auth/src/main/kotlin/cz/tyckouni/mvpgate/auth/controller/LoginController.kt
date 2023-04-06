package cz.tyckouni.mvpgate.auth.controller

import io.micrometer.tracing.Tracer
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class LoginController(
    val tracer: Tracer,
) {

    @GetMapping("/login/hello")
    fun hello(): String {
        val span = tracer.currentSpan()

        LOGGER.debug("Hello: {}", span)

        return "hello"
    }

    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(LoginController::class.java)
    }
}
