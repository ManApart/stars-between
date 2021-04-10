package org.rak.microStars.floorplan

import org.rak.microStars.tile.DistanceMap
import org.rak.microStars.tile.Tile

fun createDistancesFrom(source: Tile, area: Area): DistanceMap {
    val distanceMap = DistanceMap(source)
    if (!source.system.isSolid()) {
        val open = mutableListOf<Tile>()
        val closed = mutableListOf<Tile>()
        open.add(source)
        distanceMap.setDistance(source, 0)

        while (open.isNotEmpty()) {
            val current = open.first()
            open.remove(current)

            if (!closed.contains(current)) {
                val distance = distanceMap.getMinDistanceTo(current, area) + 1
                distanceMap.setDistance(current, distance)
                open.addAll(area.getNeighbors(current).filter { !closed.contains(it) })
            }
            closed.add(current)
        }
    }

    return distanceMap
}
