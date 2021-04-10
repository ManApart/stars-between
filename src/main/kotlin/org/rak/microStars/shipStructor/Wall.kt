package org.rak.microStars.shipStructor

import org.rak.microStars.systems.ShipSystem
import org.rak.microStars.tile.SystemType
import org.rak.microStars.views.persistence.PersistedSystem

class Wall(health: Int = 100) : ShipSystem("Wall", SystemType.WALL, health, true) {
    override fun persisted(): PersistedSystem {
        TODO("Not yet implemented")
    }
}
