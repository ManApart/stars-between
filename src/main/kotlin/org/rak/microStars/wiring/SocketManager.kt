package org.rak.microStars.wiring

import org.rak.microStars.Game
import org.rak.microStars.floorplan.SimpleFloorPlan

object SocketManager {
    val socket = Websocket()

    fun sendUpdate() {
        socket.send(SimpleFloorPlan(Game.floorPlan))
    }
}