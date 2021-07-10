package org.rak.starsBetween.power

import org.rak.starsBetween.systems.ShipSystem
import org.rak.starsBetween.tile.Tile
import org.rak.starsBetween.tile.SystemType
import org.rak.starsBetween.views.persistence.PersistedSystem

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
