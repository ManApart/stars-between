package persistence

import systems.ShipSystem
import shipStructor.SPACE_SYSTEM

class PersistedSpace : PersistedSystem() {
    override fun toSystem(): ShipSystem {
        return SPACE_SYSTEM
    }
}
