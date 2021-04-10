package org.rak.microStars.power

import org.rak.microStars.tile.Tile

interface Powerable {
    val totalPowerCapacity: Int
    val powerConsumedPerTick: Int
    var power: Int
    var lastReceivedPowerFrom: Tile?
}
