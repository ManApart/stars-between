package org.rak.starsBetween.systems

import org.rak.starsBetween.views.persistence.PersistedSystem

class PersistedShield(
    val health: Int,
    val cycleTime: Int,
    val radius: Int,
    val totalPowerCapacity: Int,
    val power: Int
) : PersistedSystem {
    constructor(shield: Shield): this(
        shield.health,
        shield.cycleTime,
        shield.radius,
        shield.totalPowerCapacity,
        shield.power
    )

    override fun toSystem(): ShipSystem {
        return Shield(health,0, cycleTime, totalPowerCapacity)
    }
}
