package org.rak.microStars.systems

import org.rak.microStars.power.Powerable
import org.rak.microStars.tile.SystemType
import org.rak.microStars.tile.Tile
import org.rak.microStars.views.persistence.PersistedSystem

class Shield(
    health: Int = 100,
    val frequency: Int = 0,
    val cycleTime: Int = 100,
    val radius: Int = 1,
    override val totalPowerCapacity: Int = 0,
) : ShipSystem("Shield", SystemType.SHIELD, health, true), Powerable {
    override var power = totalPowerCapacity
    override var lastReceivedPowerFrom: Tile? = null
    var currentPowerPull = 0

    override val powerConsumedPerTick: Int; get() = currentPowerPull

    override fun persisted(): PersistedSystem {
        return PersistedShield(this)
    }
}
