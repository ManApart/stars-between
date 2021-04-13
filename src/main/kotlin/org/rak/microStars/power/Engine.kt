package org.rak.microStars.power

import org.rak.microStars.systems.ShipSystem
import org.rak.microStars.tile.Tile
import org.rak.microStars.tile.SystemType
import org.rak.microStars.views.persistence.PersistedSystem
import kotlin.math.min

class Engine(health: Int = 100, val powerProduced: Int = 100, val totalPowerCapacity: Int = 100) : ShipSystem("Engine", SystemType.ENGINE, health, true) {
    var power = totalPowerCapacity

    override fun tick(parent: Tile) {
        power = min(power + powerProduced, totalPowerCapacity)
    }

    override fun persisted(): PersistedSystem {
        return PersistedEngine(this)
    }
}
