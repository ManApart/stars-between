package persistence

import kotlinx.serialization.Serializable
import power.Engine
import shipStructor.SPACE_SYSTEM
import systems.ShipSystem

@Serializable
class PersistedSpace : PersistedSystem {
    override fun toSystem(): ShipSystem {
        return SPACE_SYSTEM
    }
}


