package persistence

import systems.ShipSystem
import shipStructor.Floor

class PersistedFloor(
    val health: Int
) : PersistedSystem() {
    constructor(floor: Floor) : this(
        floor.health
    )

    override fun toSystem(): ShipSystem {
        return Floor().also { it.health = health }
    }
}
