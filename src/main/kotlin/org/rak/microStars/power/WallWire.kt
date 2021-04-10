package org.rak.microStars.power

import org.rak.microStars.systems.ShipSystem
import org.rak.microStars.tile.Tile

class WallWire(health: Int, val totalPowerCapacity: Int = 5) : ShipSystem("Wall Wire", health, true) {
    var power = totalPowerCapacity
    var lastReceivedPowerFrom: Tile? = null
}
