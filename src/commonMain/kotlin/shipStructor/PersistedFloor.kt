package shipStructor

import systems.ShipSystem
import persistence.PersistedSystem

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
