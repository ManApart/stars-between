package org.rak.microStars

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

const val gameTickFrequency = 1000L
const val broadcastFrequency = 200L

@SpringBootApplication
class MicroStarsApplication


fun main(args: Array<String>) {
    val socket = Websocket()

    val gameThread = Thread {
        while (true) {
            Game.tick()
            Thread.sleep(gameTickFrequency)
        }
    }
    gameThread.start()

    val broadCastThread = Thread {
        while (true) {
            socket.send(SimpleFloorPlan(Game.floorPlan))
            Thread.sleep(broadcastFrequency)
        }
    }
    broadCastThread.start()

    runApplication<MicroStarsApplication>(*args)
}