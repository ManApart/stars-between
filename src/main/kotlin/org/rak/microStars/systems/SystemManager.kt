package org.rak.microStars.systems

import org.rak.microStars.floorplan.FloorPlan
import org.rak.microStars.tile.Tile
import org.rak.microStars.tile.TileType
import kotlin.math.abs

fun tickSystems(floorPlan: FloorPlan){
    floorPlan.getAllTiles().forEach {
        tick(it)
    }
}

private fun tick(tile: Tile) {
    when (tile.type){
        TileType.VENT -> produceAir(tile)
    }
}

private fun produceAir(tile: Tile) {
    if (tile.power > abs(tile.powerProduced)){
        tile.power -= tile.powerProduced
        tile.air += tile.airProduced
    }
}
