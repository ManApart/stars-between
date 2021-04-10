package org.rak.microStars.views.persistence

import com.fasterxml.jackson.annotation.JsonTypeInfo
import org.rak.microStars.systems.ShipSystem

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "className")
interface PersistedSystem {
    fun toSystem(): ShipSystem
}

