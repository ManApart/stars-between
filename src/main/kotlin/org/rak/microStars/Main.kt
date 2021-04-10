package org.rak.microStars

import org.rak.microStars.views.overview.SimpleFloorPlan
import org.rak.microStars.game.Game
import org.rak.microStars.wiring.SocketManager
import org.rak.microStars.wiring.loadGame
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import kotlin.concurrent.fixedRateTimer

const val gameTickFrequency = 200L
const val broadcastFrequency = 200L

@SpringBootApplication
class MicroStarsApplication

fun main(args: Array<String>) {
    loadGame()

    fixedRateTimer("gametick", true, 0L, gameTickFrequency){
        Game.tick()
    }

    fixedRateTimer("broadcast", true, 0L, broadcastFrequency){
        SocketManager.socket.send(SimpleFloorPlan(Game.floorPlan))
    }

    runApplication<MicroStarsApplication>(*args)
}
