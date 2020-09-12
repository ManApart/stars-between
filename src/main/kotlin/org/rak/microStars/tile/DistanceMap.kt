package org.rak.microStars.tile

import org.rak.microStars.floorplan.Area
import org.rak.microStars.floorplan.FloorPlan
import kotlin.math.min
//Should be specific to an area
class DistanceMap(val source: Tile) {
    val costs = mutableMapOf<Int, MutableMap<Int, Int>>()
    val nearestType = mutableMapOf<TileType, Tile?>()

    fun setDistance(source: Tile, distance: Int) {
        val x = source.position.x
        val y = source.position.y

        costs.putIfAbsent(x, mutableMapOf())
        val current = costs[x]!![y] ?: distance
        costs[x]!![y] = min(distance, current)
    }

    fun getMinDistanceTo(tile: Tile, area: Area): Int {
        return area.getNeighbors(tile).map { getDistance(it) }.filter { it != Int.MAX_VALUE }.minOrNull() ?: 0
    }

    fun getDistance(tile: Tile): Int {
        return getDistance(tile.position.x, tile.position.y)
    }

    fun getDistance(x: Int, y: Int): Int {
        return costs[x]?.getOrDefault(y, Int.MAX_VALUE) ?: Int.MAX_VALUE
    }

    fun getNearestTileOfType(tileType: TileType, area: Area): Tile? {
        if (!nearestType.containsKey(tileType)) {
            nearestType[tileType] = findNearestTileOfType(tileType, area)
        }
        return nearestType[tileType]
    }

    private fun findNearestTileOfType(tileType: TileType, area: Area): Tile? {
        val tilesOfType = area.tiles.filter { it.type == tileType }
        return nearest(tilesOfType)
    }

    fun nearest(tiles: List<Tile>) : Tile? {
        return tiles.filter { getDistance(it) != Int.MAX_VALUE }.minByOrNull { getDistance(it) }
    }

    fun hasPathTo(source: Tile): Boolean {
        return costs.containsKey(source.position.x) && costs[source.position.x]!!.containsKey(source.position.y)
    }

    fun getNextStep(source: Tile, area: Area) : Tile? {
        val nearestNeighbor = area.getNeighbors(source).minByOrNull { getDistance(it) }

        if (nearestNeighbor != null && getDistance(nearestNeighbor) < getDistance(source)){
            return nearestNeighbor
        }

        return null
    }

}