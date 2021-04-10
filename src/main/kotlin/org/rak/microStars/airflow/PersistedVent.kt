package org.rak.microStars.airflow

import org.rak.microStars.systems.ShipSystem
import org.rak.microStars.views.persistence.PersistedSystem

class PersistedVent(
    val health: Int,
    val airProduced: Int,
    val totalPowerCapacity: Int,
    val powerConsumedPerTick: Int,
    val power: Int
) : PersistedSystem {

    constructor(vent: Vent) : this(
        vent.health,
        vent.airProduced,
        vent.totalPowerCapacity,
        vent.powerConsumedPerTick,
        vent.power
    )

    override fun toSystem(): ShipSystem {
        return Vent(health, airProduced, totalPowerCapacity, powerConsumedPerTick).also { it.power = power }
    }
}
