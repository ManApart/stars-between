package org.rak.starsBetween.systems

import org.rak.starsBetween.views.persistence.PersistedSystem

class PersistedShield(
    val health: Int,
    val radius: Int,
    val powerToStrength: Float,
    val minPowerConsumedPerTick: Int,
    val maxPowerConsumedPerTick: Int,
    val totalPowerCapacity: Int,
    val power: Int
) : PersistedSystem {
    constructor(shield: Shield): this(
        shield.health,
        shield.radius,
        shield.powerToStrength,
        shield.minPowerConsumedPerTick,
        shield.maxPowerConsumedPerTick,
        shield.totalPowerCapacity,
        shield.power
    )

    override fun toSystem(): ShipSystem {
        return Shield(health,0, radius, powerToStrength, minPowerConsumedPerTick, maxPowerConsumedPerTick, totalPowerCapacity)
    }
}
