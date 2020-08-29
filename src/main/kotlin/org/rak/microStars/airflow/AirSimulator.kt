package org.rak.microStars.airflow

import org.rak.microStars.floorplan.FloorPlan
import org.rak.microStars.tile.Tile
import kotlin.math.max
import kotlin.math.min

const val airTakenPerTick = 50

fun simulateAir(floorPlan: FloorPlan){
    floorPlan.getAllTiles().filter { !it.isSolid() }.sortedBy { it.air }.forEach {
        consumeOrProduceAir(it)
        takeAir(it, floorPlan)
    }
}

fun consumeOrProduceAir(tile: Tile){
    tile.air = min(100, max(0, tile.air + tile.airProduced))
}

fun takeAir(tile: Tile, floorPlan: FloorPlan) {
    val neighborWithHighestPressure = floorPlan.getNeighbors(tile).maxByOrNull { it.air }
    if (neighborWithHighestPressure != null && neighborWithHighestPressure.air > tile.air){
        val availableDifference = neighborWithHighestPressure.air - tile.air
        val airToTake = min(airTakenPerTick, availableDifference/2)
        tile.air = tile.air + airToTake
        neighborWithHighestPressure.air = neighborWithHighestPressure.air - airToTake
    }
}
