package org.rak.starsBetween.airflow

import org.rak.starsBetween.power.Powerable
import org.rak.starsBetween.systems.ShipSystem
import org.rak.starsBetween.tile.SystemType
import org.rak.starsBetween.tile.Tile
import org.rak.starsBetween.persistence.PersistedSystem
import kotlin.math.abs

class Vent(
    health: Int = 100,
    val airProduced: Int = 100,
    override val totalPowerCapacity: Int = 0,
    override val powerConsumedPerTick: Int = 0
) : ShipSystem("Vent", SystemType.VENT, health), Powerable {
    override var power = totalPowerCapacity
    override var lastReceivedPowerFrom: Tile? = null

    override fun tick(parent: Tile) {
        if (power >= abs(powerConsumedPerTick)) {
            power -= powerConsumedPerTick
            parent.air += airProduced
        }
    }

    override fun persisted(): PersistedSystem {
        return PersistedVent(this)
    }

}
