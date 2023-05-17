package cz.tyckouni.mvpgate.party

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * Party application runner class.
 */
@SpringBootApplication
class PartyApplication

fun main(args: Array<String>) {
    runApplication<PartyApplication>(*args)
}
