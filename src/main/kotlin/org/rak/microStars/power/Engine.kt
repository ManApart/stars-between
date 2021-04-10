package org.rak.microStars.power

import org.rak.microStars.systems.ShipSystem
import org.rak.microStars.tile.Tile

class Engine(health: Int, val powerProduced: Int = 100, val totalPowerCapacity: Int = 100) : ShipSystem("Engine", health, true) {
    var power = totalPowerCapacity
    var lastReceivedPowerFrom: Tile? = null
}
