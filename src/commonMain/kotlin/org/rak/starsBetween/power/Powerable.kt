package org.rak.starsBetween.power

import org.rak.starsBetween.tile.Tile

interface Powerable {
    val totalPowerCapacity: Int
    val powerConsumedPerTick: Int
    var power: Int
    var lastReceivedPowerFrom: Tile?
}
