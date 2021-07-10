package shipStructor

import persistence.PersistedFloor
import systems.ShipSystem
import tile.SystemType
import persistence.PersistedSystem

class Floor : ShipSystem("Floor", SystemType.FLOOR) {
    override fun persisted(): PersistedSystem {
        return PersistedFloor(this)
    }
}
