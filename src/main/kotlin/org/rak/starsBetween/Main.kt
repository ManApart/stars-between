package org.rak.starsBetween

import org.rak.starsBetween.game.Game
import org.rak.starsBetween.views.ViewWrapper
import org.rak.starsBetween.wiring.SocketManager
import org.rak.starsBetween.wiring.loadGame
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
        SocketManager.socket.send(ViewWrapper(Game.currentView))
    }

    runApplication<MicroStarsApplication>(*args)
}
