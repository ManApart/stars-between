package org.rak.microStars.power

import org.rak.microStars.systems.ShipSystem
import org.rak.microStars.tile.Tile

class FloorWire(health: Int, val totalPowerCapacity: Int = 5) : ShipSystem("Floor Wire", health, false) {
    var power = totalPowerCapacity
    var lastReceivedPowerFrom: Tile? = null
}
