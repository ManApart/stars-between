package org.rak.starsBetween.views.persistence

import com.fasterxml.jackson.annotation.JsonTypeInfo
import org.rak.starsBetween.systems.ShipSystem

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "className")
interface PersistedSystem {
    fun toSystem(): ShipSystem
}

