package power

import systems.ShipSystem
import tile.Tile
import tile.SystemType
import persistence.PersistedSystem
import persistence.PersistedWire

class Wire(
    name: String,
    type: SystemType,
    health: Int = 100,
    override val totalPowerCapacity: Int = 5
) : ShipSystem(name, type, health, type == SystemType.WIRE_WALL), Powerable {
    override val powerConsumedPerTick = 0
    override var power = totalPowerCapacity
    override var lastReceivedPowerFrom: Tile? = null

    override fun persisted(): PersistedSystem {
        return PersistedWire(this)
    }
}
