package org.rak.microStars.power

import org.rak.microStars.systems.ShipSystem
import org.rak.microStars.tile.Tile
import org.rak.microStars.tile.SystemType

class Engine(health: Int = 100, val powerProduced: Int = 100, val totalPowerCapacity: Int = 100) : ShipSystem("Engine", SystemType.ENGINE, health,true) {
    var power = totalPowerCapacity

    override fun tick(parent: Tile) {
        power += powerProduced
    }
}
