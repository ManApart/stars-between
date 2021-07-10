package persistence

import kotlinx.serialization.Serializable
import systems.ShipSystem

//@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "className")
@Serializable(with = PersistedSystemSerializer::class)
sealed interface PersistedSystem {
    fun toSystem(): ShipSystem
}

