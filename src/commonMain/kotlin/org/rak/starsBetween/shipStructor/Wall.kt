package org.rak.starsBetween.shipStructor

import org.rak.starsBetween.systems.ShipSystem
import org.rak.starsBetween.tile.SystemType
import org.rak.starsBetween.persistence.PersistedSystem

class Wall(health: Int = 100) : ShipSystem("Wall", SystemType.WALL, health, true) {
    override fun persisted(): PersistedSystem {
        return PersistedWall(this)
    }
}
