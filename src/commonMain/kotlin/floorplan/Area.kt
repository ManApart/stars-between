package floorplan

import tile.Tile

class Area(val tiles: List<Tile>, private val isValidForDistance: (Tile) -> Boolean) {
    private val tileMap = buildTileMap(tiles)

    init {
        tiles.forEach { it.distanceMap = createDistancesFrom(it, this) }
    }

    private fun buildTileMap(tiles: List<Tile>): Map<Int, Map<Int, Tile>> {
        val map = mutableMapOf<Int, MutableMap<Int, Tile>>()
        tiles.forEach {tile ->
            map.putIfAbsent(tile.position.y, mutableMapOf())
            map[tile.position.y]!![tile.position.x] = tile
        }
        return map
    }

    fun getNeighbors(tile: Tile): List<Tile> {
        return tile.position.neighbors().mapNotNull { getTile(it) }
    }

    private fun getTile(position: Position) : Tile? {
        return tileMap[position.y]?.get(position.x)
    }

    fun findRoute(source: Tile, destination: Tile): List<Tile> {
        val route = mutableListOf<Tile>()

        //Don't find a route if not part of this area
        if (!tiles.contains(source)){
            return route
        }

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
