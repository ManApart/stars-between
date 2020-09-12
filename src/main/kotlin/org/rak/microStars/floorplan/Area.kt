package org.rak.microStars.floorplan

import org.rak.microStars.tile.DEFAULT_TILE
import org.rak.microStars.tile.Tile

class Area(val parent: FloorPlan, val isValidForDistance: (Tile) -> Boolean) {


    fun getTiles(): List<Tile> {
        return listOf()
    }

    fun getNeighbors(tile: Tile): List<Tile> {
        return parent.getNeighbors(tile).filter { getTiles().contains(it) }
    }

}