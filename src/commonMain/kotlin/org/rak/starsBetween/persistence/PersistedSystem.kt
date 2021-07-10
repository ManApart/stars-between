package org.rak.starsBetween.persistence

//import com.fasterxml.jackson.annotation.JsonTypeInfo
import org.rak.starsBetween.systems.ShipSystem

//@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "className")
interface PersistedSystem {
    fun toSystem(): ShipSystem
}

