package shipStructor

import systems.ShipSystem
import persistence.PersistedSystem

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
