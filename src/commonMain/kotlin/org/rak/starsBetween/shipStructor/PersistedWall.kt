package org.rak.starsBetween.shipStructor

import org.rak.starsBetween.systems.ShipSystem
import org.rak.starsBetween.persistence.PersistedSystem

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
