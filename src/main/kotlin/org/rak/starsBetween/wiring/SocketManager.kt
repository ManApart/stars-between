package org.rak.starsBetween.wiring

import org.rak.starsBetween.game.Game
import org.rak.starsBetween.views.ViewWrapper

object SocketManager {
    val socket = Websocket()

    fun sendUpdate() {
        socket.send(ViewWrapper(Game.currentView))
    }
}
