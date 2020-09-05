package org.rak.microStars

import org.rak.microStars.floorplan.FloorPlan
import org.rak.microStars.tile.FLOOR
import org.rak.microStars.tile.SPACE
import org.rak.microStars.tile.WALL

/**
 * 0 = space
 * 1 = floor
 * 2 = wall
 */
fun createFloorPlanWithTiles(plan: List<List<Int>>): FloorPlan {
    val floorPlan = FloorPlan(plan.size)
    plan.toMap().entries.forEach { (y, entry) ->
        entry.entries.forEach { (x, tileId) ->
            val tile = when (tileId) {
                0 -> SPACE
                1 -> FLOOR
                else -> WALL
            }
            floorPlan.setTileWithoutUpdates(tile, x, y)
        }
    }
    return floorPlan
}