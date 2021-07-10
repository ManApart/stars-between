package org.rak.starsBetween.shipStructor

import org.rak.starsBetween.systems.ShipSystem
import org.rak.starsBetween.tile.SystemType
import org.rak.starsBetween.persistence.PersistedSystem

val SPACE_SYSTEM = Space()
private val persistedSpace = PersistedSpace()

class Space : ShipSystem("Space" , SystemType.SPACE, 0, false) {
    val airConsumed = 20
    override fun persisted(): PersistedSystem {
        return persistedSpace
    }
}
