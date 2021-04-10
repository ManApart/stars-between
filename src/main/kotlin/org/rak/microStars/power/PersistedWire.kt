package org.rak.microStars.power

import org.rak.microStars.systems.ShipSystem
import org.rak.microStars.tile.SystemType
import org.rak.microStars.views.persistence.PersistedSystem

class PersistedWire(
    val name: String,
    val type: SystemType,
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
        return Wire(name, type, health, totalPowerCapacity).also { it.power = power }
    }
}
