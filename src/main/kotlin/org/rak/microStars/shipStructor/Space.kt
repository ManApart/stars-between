package org.rak.microStars.shipStructor

import org.rak.microStars.systems.ShipSystem
import org.rak.microStars.tile.SystemType
import org.rak.microStars.views.persistence.PersistedSystem

val SPACE_SYSTEM = Space()
private val persistedSpace = PersistedSpace()

class Space : ShipSystem("Space" , SystemType.SPACE, 0, false) {
    override fun persisted(): PersistedSystem {
        return persistedSpace
    }
}
