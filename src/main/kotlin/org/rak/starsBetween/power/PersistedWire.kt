package org.rak.starsBetween.power

import org.rak.starsBetween.systems.ShipSystem
import org.rak.starsBetween.tile.SystemType
import org.rak.starsBetween.views.persistence.PersistedSystem

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
