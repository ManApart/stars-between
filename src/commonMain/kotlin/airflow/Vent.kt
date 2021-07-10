package airflow

import power.Powerable
import systems.ShipSystem
import tile.SystemType
import tile.Tile
import persistence.PersistedSystem
import persistence.PersistedVent
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
