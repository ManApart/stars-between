package shipStructor

import systems.ShipSystem
import tile.SystemType
import persistence.PersistedSystem

val SPACE_SYSTEM = Space()
private val persistedSpace = PersistedSpace()

class Space : ShipSystem("Space" , SystemType.SPACE, 0, false) {
    val airConsumed = 20
    override fun persisted(): PersistedSystem {
        return persistedSpace
    }
}
