package org.rak.microStars.shipStructor

import org.rak.microStars.systems.ShipSystem
import org.rak.microStars.views.persistence.PersistedSystem

class PersistedSpace : PersistedSystem {
    override fun toSystem(): ShipSystem {
        return SPACE_SYSTEM
    }
}
