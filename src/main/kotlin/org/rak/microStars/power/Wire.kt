package org.rak.microStars.power

import org.rak.microStars.systems.ShipSystem
import org.rak.microStars.tile.Tile
import org.rak.microStars.tile.SystemType
import org.rak.microStars.views.persistence.PersistedSystem

class Wire(
    name: String,
    type: SystemType,
    health: Int = 100,
    override val totalPowerCapacity: Int = 5
) : ShipSystem(name, type, health, false), Powerable {
    override val powerConsumedPerTick = 0
    override var power = totalPowerCapacity
    override var lastReceivedPowerFrom: Tile? = null

    override fun persisted(): PersistedSystem {
        return PersistedWire(this)
    }
}
