package persistence

import kotlinx.serialization.Serializable
import systems.ShipSystem
import shipStructor.Floor

@Serializable
data class PersistedFloor(
    val health: Int
) : PersistedSystem {
    constructor(floor: Floor) : this(
        floor.health
    )

    override fun toSystem(): ShipSystem {
        return Floor().also { it.health = health }
    }
}
