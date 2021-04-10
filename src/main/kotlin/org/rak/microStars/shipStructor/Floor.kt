package org.rak.microStars.shipStructor

import org.rak.microStars.systems.ShipSystem
import org.rak.microStars.tile.SystemType
import org.rak.microStars.views.persistence.PersistedSystem

class Floor : ShipSystem("Floor", SystemType.FLOOR) {
    override fun persisted(): PersistedSystem {
        return PersistedFloor(this)
    }
}
