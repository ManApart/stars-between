package persistence

import systems.ShipSystem

interface PersistedSystem {
    fun toSystem(): ShipSystem
}

