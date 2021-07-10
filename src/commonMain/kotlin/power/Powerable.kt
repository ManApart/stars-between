package power

import tile.Tile

interface Powerable {
    val totalPowerCapacity: Int
    val powerConsumedPerTick: Int
    var power: Int
    var lastReceivedPowerFrom: Tile?
}
