package org.rak.microStars.floorplan

import org.rak.microStars.tile.*

class FloorPlan(val size: Int = 5) {
    private val tiles = (0 until size).associate { y ->
        (y to (0 until size).associateWith { x ->
            SPACE.copy(position = Position(x, y))
        }.toMutableMap())
    }

    fun getTileMap(): Map<Int, Map<Int, Tile>> {
        return tiles.toMap()
    }

    fun getAllTiles(): List<Tile> {
        return tiles.values.flatMap { it.values }
    }

    fun getTile(pos: Position): Tile {
        return getTile(pos.x, pos.y)
    }

    fun getTile(x: Int, y: Int): Tile {
        return if (x in 0 until size && y in 0 until size) {
            tiles[y]!![x] ?: DEFAULT_TILE
        } else {
            DEFAULT_TILE
        }
    }

    fun setTile(tile: Tile, pos: Position) {
        setTile(tile, pos.x, pos.y)
    }

    fun setTile(tile: Tile, x: Int, y: Int) {
        if (x in 0 until size && y in 0 until size) {
            val newTile = tile.copy(position = Position(x, y))
            tiles[y]!![x] = newTile
            orient(newTile, this)
            getNeighbors(newTile).forEach { orient(it, this) }
            buildDistanceMaps()
        }
    }

    fun setTileWithoutUpdates(tile: Tile, pos: Position) {
        setTileWithoutUpdates(tile, pos.x, pos.y)
    }

    fun setTileWithoutUpdates(tile: Tile, x: Int, y: Int) {
        if (x in 0 until size && y in 0 until size) {
            val newTile = tile.copy(position = Position(x, y))
            tiles[y]!![x] = newTile
        }
    }

    fun getNeighbors(x: Int, y: Int): List<Tile> {
        return getNeighbors(getTile(x, y))
    }

    fun getNeighbors(tile: Tile): List<Tile> {
        return tile.position.neighbors().map { getTile(it) }.filter { it != DEFAULT_TILE }
    }

    fun buildDistanceMaps() {
        getAllTiles().forEach { it.distanceMap = createDistancesFrom(it, this) }
    }

    fun findRoute(source: Tile, destination: Tile): List<Tile> {
        val route = mutableListOf<Tile>()

        //Don't find a route if there is no path from destination to source
        if (!destination.distanceMap.hasPathTo(source)) {
            return route
        }

        var current: Tile? = source
        while (current != destination && current != null) {
            current = destination.distanceMap.nearest(getNeighbors(current))
            if (current != null) {
                route.add(current)
            }
        }

        return route
    }

}

fun fromSimpleFloorPlan(simpleFloorPlan: SimpleFloorPlan): FloorPlan {
    val size = simpleFloorPlan.tiles.flatten().maxBy { it.x }?.x ?: 10
    val floorPlan = FloorPlan(size)
    simpleFloorPlan.tiles.flatten().map {
        floorPlan.setTileWithoutUpdates(fromSimpleTile(it), it.x, it.y)
    }
    floorPlan.getAllTiles().forEach { orient(it, floorPlan) }
    floorPlan.buildDistanceMaps()

    return floorPlan
}