package org.rak.microStars.shipStructor

import org.rak.microStars.systems.ShipSystem
import org.rak.microStars.views.persistence.PersistedSystem

class PersistedFloor(
    val health: Int
) : PersistedSystem{
    constructor(floor: Floor): this(
        floor.health
    )
    override fun toSystem(): ShipSystem {
        return Floor().also { it.health = health }
    }
}
