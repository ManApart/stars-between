package org.rak.microStars

import org.rak.microStars.floorplan.SimpleFloorPlan
import org.rak.microStars.wiring.SocketManager
import org.rak.microStars.wiring.loadGame
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

const val gameTickFrequency = 1000L
const val broadcastFrequency = 1000L

@SpringBootApplication
class MicroStarsApplication


fun main(args: Array<String>) {
    loadGame()
    val gameThread = Thread {
        while (true) {
            Game.tick()
            Thread.sleep(gameTickFrequency)
        }
    }
    gameThread.start()

    val broadCastThread = Thread {
        while (true) {
            SocketManager.socket.send(SimpleFloorPlan(Game.floorPlan))
            Thread.sleep(broadcastFrequency)
        }
    }
    broadCastThread.start()

    runApplication<MicroStarsApplication>(*args)
}