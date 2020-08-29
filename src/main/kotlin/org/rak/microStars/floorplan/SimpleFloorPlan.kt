package org.rak.microStars.floorplan

import org.rak.microStars.tile.SimpleTile

class SimpleFloorPlan(floorPlan: FloorPlan) {
    val tiles = (0 until floorPlan.size).map {y ->
        (0 until floorPlan.size).map { x ->
            SimpleTile(floorPlan.getTile(x, y))
        }
    }
}