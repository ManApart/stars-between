package org.rak.microStars.tile

import org.rak.microStars.floorplan.Area
import kotlin.math.min
//Should be specific to an area
class DistanceMap(val source: Tile) {
    val costs = mutableMapOf<Int, MutableMap<Int, Int>>()
    val nearestType = mutableMapOf<SystemType, Tile?>()

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

    fun getNearestTileOfType(systemType: SystemType, area: Area): Tile? {
        if (!nearestType.containsKey(systemType)) {
            nearestType[systemType] = findNearestTileOfType(systemType, area)
        }
        return nearestType[systemType]
    }

    private fun findNearestTileOfType(systemType: SystemType, area: Area): Tile? {
        val tilesOfType = area.tiles.filter { it.system.type == systemType }
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
