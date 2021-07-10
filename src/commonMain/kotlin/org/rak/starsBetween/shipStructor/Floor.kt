package org.rak.starsBetween.shipStructor

import org.rak.starsBetween.systems.ShipSystem
import org.rak.starsBetween.tile.SystemType
import org.rak.starsBetween.persistence.PersistedSystem

class Floor : ShipSystem("Floor", SystemType.FLOOR) {
    override fun persisted(): PersistedSystem {
        return PersistedFloor(this)
    }
}
