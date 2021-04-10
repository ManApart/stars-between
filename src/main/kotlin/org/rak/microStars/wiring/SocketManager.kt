package org.rak.microStars.wiring

import org.rak.microStars.game.Game
import org.rak.microStars.views.overview.SimpleFloorPlan

object SocketManager {
    val socket = Websocket()

    fun sendUpdate() {
        socket.send(SimpleFloorPlan(Game.floorPlan))
    }
}
