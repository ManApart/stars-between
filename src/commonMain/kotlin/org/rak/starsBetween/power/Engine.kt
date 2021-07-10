package org.rak.starsBetween.power

import org.rak.starsBetween.systems.ShipSystem
import org.rak.starsBetween.tile.Tile
import org.rak.starsBetween.tile.SystemType
import org.rak.starsBetween.persistence.PersistedSystem
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
