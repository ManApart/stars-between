package org.rak.microStars.shipStructor

import org.rak.microStars.systems.ShipSystem
import org.rak.microStars.tile.SystemType

class Wall(health: Int = 100) : ShipSystem("Wall", SystemType.WALL, health, true)
