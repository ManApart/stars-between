package org.rak.microStars.floorplan

import org.rak.microStars.tile.SimpleTile

class SimpleFloorPlan(val tiles: List<List<SimpleTile>>) {

    constructor(floorPlan: FloorPlan) : this((0 until floorPlan.size).map { y ->
        (0 until floorPlan.size).map { x ->
            SimpleTile(floorPlan.getTile(x, y))
        }
    })

}