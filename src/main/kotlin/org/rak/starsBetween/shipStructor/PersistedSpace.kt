package org.rak.starsBetween.shipStructor

import org.rak.starsBetween.systems.ShipSystem
import org.rak.starsBetween.views.persistence.PersistedSystem

class PersistedSpace : PersistedSystem {
    override fun toSystem(): ShipSystem {
        return SPACE_SYSTEM
    }
}
