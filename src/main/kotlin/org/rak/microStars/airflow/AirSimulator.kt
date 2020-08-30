package org.rak.microStars.airflow

import org.rak.microStars.floorplan.FloorPlan
import org.rak.microStars.tile.Tile
import kotlin.math.max
import kotlin.math.min

const val airTakenPerTick = 50

fun simulateAir(floorPlan: FloorPlan) {
    val airTiles = floorPlan.getAllTiles().filter { !it.isSolid() }

    airTiles.forEach {
        consumeOrProduceAir(it)
    }
    flowAir(airTiles, floorPlan)
}

fun consumeOrProduceAir(tile: Tile) {
    tile.air = min(100, max(0, tile.air + tile.airProduced))
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
    val avgAir = tiles.sumBy { it.air } / tiles.size
    tiles.forEach { it.air = avgAir }
}
