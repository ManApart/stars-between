package org.rak.starsBetween.shipStructor

import org.rak.starsBetween.systems.ShipSystem
import org.rak.starsBetween.views.persistence.PersistedSystem

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
