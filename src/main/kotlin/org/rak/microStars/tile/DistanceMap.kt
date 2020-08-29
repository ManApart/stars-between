package org.rak.microStars.tile

import org.rak.microStars.floorplan.FloorPlan
import kotlin.math.min

class DistanceMap(val source: Tile) {
    val costs = mutableMapOf<Int, MutableMap<Int, Int>>()

    fun setDistance(source: Tile, distance: Int) {
        val x = source.position.x
        val y = source.position.y

        costs.putIfAbsent(x, mutableMapOf())
        val current = costs[x]!![y] ?: distance
        costs[x]!![y] = min(distance, current)
    }

    fun getMinDistanceTo(tile: Tile, floorPlan: FloorPlan): Int {
       return floorPlan.getNeighbors(tile).map { getDistance(it) }.filter { it != -1 }.min() ?: 0
    }

    fun getDistance(tile: Tile) : Int {
        return costs[tile.position.x]?.getOrDefault(tile.position.y, -1) ?: -1
    }

}