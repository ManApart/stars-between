package persistence

import systems.ShipSystem
import shipStructor.Wall

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
