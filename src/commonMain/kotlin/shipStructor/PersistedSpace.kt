package shipStructor

import systems.ShipSystem
import persistence.PersistedSystem

class PersistedSpace : PersistedSystem {
    override fun toSystem(): ShipSystem {
        return SPACE_SYSTEM
    }
}
