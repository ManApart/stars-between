package org.rak.starsBetween

import org.rak.starsBetween.floorplan.FloorPlan
import org.rak.starsBetween.tile.FLOOR
import org.rak.starsBetween.tile.SPACE
import org.rak.starsBetween.tile.WALL

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
