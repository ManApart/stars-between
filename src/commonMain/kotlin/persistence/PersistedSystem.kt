package persistence

import kotlinx.serialization.Serializable
import systems.ShipSystem

@Serializable
sealed class PersistedSystem {
    abstract fun toSystem(): ShipSystem
}