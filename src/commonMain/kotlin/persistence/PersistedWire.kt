package persistence

import systems.ShipSystem
import tile.SystemType
import power.Wire

class PersistedWire(
    val name: String,
    val type: SystemType,
    val health: Int,
    val totalPowerCapacity: Int,
    val power: Int
) : PersistedSystem(){
    constructor(wire: Wire) : this(
        wire.name,
        wire.type,
        wire.health,
        wire.totalPowerCapacity,
        wire.power
    )
    override fun toSystem(): ShipSystem {
        return Wire(name, type, health, totalPowerCapacity).also { it.power = power }
    }
}
