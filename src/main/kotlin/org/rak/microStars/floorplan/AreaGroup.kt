package org.rak.microStars.floorplan

import org.rak.microStars.tile.Tile

class AreaGroup(parent: FloorPlan, isValidForDistance: (Tile) -> Boolean) {
    val areas: List<Area> by lazy { calculateAreas(parent, isValidForDistance)}

    private fun calculateAreas(parent: FloorPlan, isValidForDistance: (Tile) -> Boolean) : List<Area>{
        val areas = mutableListOf<Area>()
        val tiles = parent.getAllTiles().filter { isValidForDistance(it) }.toMutableList()

        while (tiles.isNotEmpty()){
            val areaTiles = createAreaTiles(parent, isValidForDistance, tiles)
            areas.add(Area(areaTiles, isValidForDistance))
            tiles.removeAll(areaTiles)
        }

        return areas
    }

    private fun createAreaTiles(parent: FloorPlan, isValidForDistance: (Tile) -> Boolean, candidates: List<Tile>): List<Tile> {
        val source = candidates.first()
        val connected = mutableListOf(source)

        val open = mutableListOf(source)
        val closed = mutableListOf<Tile>()

        while (open.isNotEmpty()){
            val current = open.first()
            open.remove(current)
            if (!closed.contains(current)){
                connected.add(current)
                val neighbors = parent.getNeighbors(current).filter { isValidForDistance(it) }
                open.addAll(neighbors)
            }
            closed.add(current)
        }

        return connected
    }

}