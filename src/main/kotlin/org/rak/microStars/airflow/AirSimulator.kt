package org.rak.microStars.airflow

import org.rak.microStars.floorplan.Area
import org.rak.microStars.floorplan.FloorPlan
import org.rak.microStars.tile.Tile
import org.rak.microStars.tile.TileType
import kotlin.math.max
import kotlin.math.min

fun simulateAir(floorPlan: FloorPlan) {
    floorPlan.airAreas.areas.forEach { area ->
        consumeAir(area)
        flowAir(area)
        moveLowAirTowardsExits(area)
        clampAir(area)
    }
}

private fun consumeAir(area: Area) {
    area.tiles.filter { it.airProduced < 0 }.forEach { tile ->
        tile.air += tile.airProduced
    }
}

private fun clampAir(area: Area) {
    area.tiles.forEach { tile ->
        tile.air = min(100, max(0, tile.air))
    }
}

private fun flowAir(area: Area) {
    val maxAir = area.tiles.maxOf { it.air }
    val open = area.tiles.filter { it.air == maxAir }.toMutableList()
    val closed = mutableListOf<Tile>()

    while (open.isNotEmpty()) {
        val current = open.first()
        open.remove(current)
        if (current.air > 0 && !closed.contains(current)) {
            val neighbors = area.getNeighbors(current).filter { it.air != 100 }
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
        val spaceTile = tiles.firstOrNull { it.type == TileType.SPACE && it.air <= 100 - remainder }
        if (spaceTile != null) {
            spaceTile.air += remainder
        } else {
            tiles.reversed().subList(0, remainder).forEach { it.air = avgAir + 1 }
        }
    }
}

fun moveLowAirTowardsExits(area: Area) {
    area.tiles.filter { it.type != TileType.SPACE && it.air == 1 }.forEach { tile ->
        val spaceTile = tile.distanceMap.getNearestTileOfType(TileType.SPACE, area)
        if (spaceTile != null) {
            val nextStep = spaceTile.distanceMap.getNextStep(tile, area)
            if (nextStep != null && nextStep.air < 100) {
                tile.air--
                nextStep.air++
            }
        }
    }
}
