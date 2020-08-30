package org.rak.microStars.airflow

import org.rak.microStars.floorplan.FloorPlan
import org.rak.microStars.tile.Tile
import kotlin.math.max
import kotlin.math.min

fun simulateAir(floorPlan: FloorPlan) {
    val airTiles = floorPlan.getAllTiles().filter { !it.isSolid() }

    consumeOrProduceAir(airTiles)
//    evaporate(airTiles)
    flowAir(airTiles, floorPlan)
    //do another pass where each tile with 1 air finds closest space tile and 'leaks' towards it
}

private fun consumeOrProduceAir(airTiles: List<Tile>) {
    airTiles.forEach { tile ->
        tile.air = min(100, max(0, tile.air + tile.airProduced))
    }
}

fun evaporate(airTiles: List<Tile>) {
    //Randomly subtract a single air to keep things from getting stale
    val tile = airTiles.filter { it.air > 0 }.randomOrNull()
    if (tile != null) {
        tile.air = tile.air - 1
    }
}

private fun flowAir(airTiles: List<Tile>, floorPlan: FloorPlan) {
    val maxAir = airTiles.maxOf { it.air }
    val open = airTiles.filter { it.air == maxAir }.toMutableList()
    val closed = mutableListOf<Tile>()

    while (open.isNotEmpty()) {
        val current = open.first()
        open.remove(current)
        if (current.air > 0 && !closed.contains(current)) {
            val neighbors = floorPlan.getNeighbors(current).filter { !it.isSolid() && it.air != 100 }
            pushAir(current, neighbors)
            open.addAll(neighbors)
        }
        closed.add(current)
    }
}

fun pushAir(source: Tile, others: List<Tile>) {
    val tiles = others + source
    val totalAir = tiles.sumBy { it.air }
    val avgAir = totalAir / tiles.size
    val remainder = totalAir % tiles.size
    tiles.forEach { it.air = avgAir }
    if (remainder > 0) {
        tiles.reversed().subList(0, remainder).forEach { it.air = avgAir + 1 }
    }
}
