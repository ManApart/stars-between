package org.rak.microStars.airflow

import org.rak.microStars.power.Powerable
import org.rak.microStars.systems.ShipSystem
import org.rak.microStars.tile.SystemType
import org.rak.microStars.tile.Tile
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

}
