package org.rak.microStars.airflow

import org.rak.microStars.systems.ShipSystem
import org.rak.microStars.views.persistence.PersistedSystem

class PersistedVent(vent: Vent) : PersistedSystem{
    override fun toSystem(): ShipSystem {
        TODO("Not yet implemented")
    }
}
