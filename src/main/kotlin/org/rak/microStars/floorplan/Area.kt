package org.rak.microStars.floorplan

import org.rak.microStars.tile.Tile

class Area(val parent: FloorPlan, val tiles: List<Tile>, val isValidForDistance: (Tile) -> Boolean) {


    fun getNeighbors(tile: Tile): List<Tile> {
        return parent.getNeighbors(tile).filter { tiles.contains(it) }
    }

}