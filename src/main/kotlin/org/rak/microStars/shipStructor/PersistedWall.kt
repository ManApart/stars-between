package org.rak.microStars.shipStructor

import org.rak.microStars.systems.ShipSystem
import org.rak.microStars.views.persistence.PersistedSystem

class PersistedWall(
    val health: Int
) : PersistedSystem{
    constructor(wall: Wall): this(
        wall.health
    )
    override fun toSystem(): ShipSystem {
        return Wall().also { it.health = health }
    }
}
