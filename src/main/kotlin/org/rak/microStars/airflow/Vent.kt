package org.rak.microStars.airflow

import org.rak.microStars.systems.ShipSystem

class Vent(health: Int = 100, val airProduced: Int = 100, val powerNeeded: Int) : ShipSystem("Vent", health) {
}
