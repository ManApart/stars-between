package persistence

import kotlinx.serialization.Serializable
import systems.ShipSystem
import tile.SystemType
import power.Wire

@Serializable
class PersistedWire(
    val name: String,
    val systemType: SystemType,
    val health: Int,
    val totalPowerCapacity: Int,
    val power: Int
) : PersistedSystem{
    constructor(wire: Wire) : this(
        wire.name,
        wire.type,
        wire.health,
        wire.totalPowerCapacity,
        wire.power
    )
    override fun toSystem(): ShipSystem {
        return Wire(name, systemType, health, totalPowerCapacity).also { it.power = power }
    }
}
