package org.rak.microStars.shipStructor

import org.rak.microStars.systems.ShipSystem
import org.rak.microStars.tile.SystemType

val SPACE_SYSTEM = Space()

class Space : ShipSystem("Space" , SystemType.SPACE, 0, false)
