package cz.tyckouni.mvpgate.auth.controller

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class LoginController {

    @GetMapping("/login/hello")
    fun hello(): String {
        LOGGER.debug("Hello")

        return "hello"
    }

    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(LoginController::class.java)
    }
}
