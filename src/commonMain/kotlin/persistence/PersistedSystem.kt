package persistence

import kotlinx.serialization.Polymorphic
import kotlinx.serialization.Serializable
import systems.ShipSystem

//@Serializable
//@Polymorphic
//abstract class PersistedSystem2 {
//    abstract fun toSystem(): ShipSystem
//}

interface PersistedSystem {
    fun toSystem(): ShipSystem
}

