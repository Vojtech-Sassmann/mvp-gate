package cz.tyckouni.mvpgate.party.application

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * Party application runner
 */
@SpringBootApplication
class PartyApplication

fun main(args: Array<String>) {
    runApplication<PartyApplication>(*args)
}
