package org.rak.microStars.airflow

import org.rak.microStars.floorplan.FloorPlan
import org.rak.microStars.tile.SPACE
import org.rak.microStars.tile.Tile
import kotlin.math.max
import kotlin.math.min

fun simulateAir(floorPlan: FloorPlan) {
    val airTiles = floorPlan.getAllTiles().filter { !it.isSolid() }

    consumeOrProduceAir(airTiles)
    flowAir(airTiles, floorPlan)
    moveLowAirTowardsExits(airTiles, floorPlan)
    clampAir(airTiles)
}

private fun consumeOrProduceAir(airTiles: List<Tile>) {
    airTiles.forEach { tile ->
        tile.air = tile.air + tile.airProduced
    }
}

private fun clampAir(airTiles: List<Tile>) {
    airTiles.forEach { tile ->
        tile.air = min(100, max(0, tile.air))
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
        val spaceTile = tiles.firstOrNull { it.isType(SPACE) && it.air <= 100 - remainder }
        if (spaceTile != null) {
            spaceTile.air += remainder
        } else {
            tiles.reversed().subList(0, remainder).forEach { it.air = avgAir + 1 }
        }
    }
}

fun moveLowAirTowardsExits(airTiles: List<Tile>, floorPlan: FloorPlan) {
    airTiles.filter { !it.isType(SPACE) && it.air == 1 }.forEach { tile ->
        val spaceTile = tile.distanceMap.getNearestTileOfType(SPACE, floorPlan)
        if (spaceTile != null) {
            val nextStep = spaceTile.distanceMap.getNextStep(tile, floorPlan)
            if (nextStep != null && nextStep.air < 100) {
                tile.air--
                nextStep.air++
            }
        }
    }
}
