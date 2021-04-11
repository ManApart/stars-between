package org.rak.microStars.wiring

import org.rak.microStars.game.Game
import org.rak.microStars.views.ViewWrapper

object SocketManager {
    val socket = Websocket()

    fun sendUpdate() {
        socket.send(ViewWrapper(Game.currentView))
    }
}
