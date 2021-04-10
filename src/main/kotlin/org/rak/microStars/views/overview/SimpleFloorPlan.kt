package org.rak.microStars.views.overview

import org.rak.microStars.floorplan.FloorPlan

class SimpleFloorPlan(val tiles: List<List<SimpleTile>>) {

    constructor(floorPlan: FloorPlan) : this((0 until floorPlan.size).map { y ->
        (0 until floorPlan.size).map { x ->
            SimpleTile(floorPlan.getTile(x, y))
        }
    })

}
