package persistence

//import com.fasterxml.jackson.annotation.JsonTypeInfo
import systems.ShipSystem

//@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "className")
interface PersistedSystem {
    fun toSystem(): ShipSystem
}

