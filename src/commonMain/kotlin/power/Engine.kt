package power

import persistence.PersistedEngine
import systems.ShipSystem
import tile.Tile
import tile.SystemType
import persistence.PersistedSystem
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
