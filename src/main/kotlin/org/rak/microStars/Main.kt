package org.rak.microStars

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

const val tickFrequency = 1000000L

@SpringBootApplication
class MicroStarsApplication


fun main(args: Array<String>) {
    val socket = Websocket()

    Thread {
        while (true) {
            Game.tick()
            socket.send(SimpleFloorPlan(Game.floorPlan))
            Thread.sleep(tickFrequency)
        }
    }.start()

    runApplication<MicroStarsApplication>(*args)
}