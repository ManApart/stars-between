package org.rak.microStars.power

import org.rak.microStars.floorplan.FloorPlan
import org.rak.microStars.tile.Tile
import org.rak.microStars.tile.TileType
import kotlin.math.max
import kotlin.math.min

fun simulatePower(floorPlan: FloorPlan) {
    val powerTiles = floorPlan.getAllTiles().filter { it.totalPowerCapacity > 0 }

    if (powerTiles.isEmpty()) {
        return
    }

    flowPower(powerTiles, floorPlan)
    clampPower(powerTiles)
}

private fun flowPower(powerTiles: List<Tile>, floorPlan: FloorPlan) {
    val open = powerTiles.filter { it.type == TileType.VENT }.toMutableList()
    val closed = mutableListOf<Tile>()

    while (open.isNotEmpty()) {
        val current = open.first()
        open.remove(current)
        if (current.totalPowerCapacity > 0 && !closed.contains(current)) {
            val neighbors =
                floorPlan.getNeighbors(current).filter { it.totalPowerCapacity > 0 && !closed.contains(it) }
            pullPower(current, neighbors)

            open.addAll(neighbors)
        }
        closed.add(current)
    }
}

private fun pullPower(source: Tile, others: List<Tile>) {
    //if multiple have same low power, user more distance from source?
    val winner = others.maxByOrNull { it.power }
    if (winner != null) {
        val need = source.totalPowerCapacity - source.power
        val given = min(need, winner.power)
        source.power += given
        winner.power -= given
    }

}

private fun clampPower(powerTiles: List<Tile>) {
    powerTiles.forEach {
        it.power = min(it.totalPowerCapacity, max(0, it.power))
    }
}