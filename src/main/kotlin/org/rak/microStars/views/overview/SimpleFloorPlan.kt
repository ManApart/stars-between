package org.rak.microStars.views.overview

import org.rak.microStars.floorplan.FloorPlan
import org.rak.microStars.views.View

class SimpleFloorPlan(val tiles: List<List<SimpleTile>>) : View {

    constructor(floorPlan: FloorPlan) : this((0 until floorPlan.size).map { y ->
        (0 until floorPlan.size).map { x ->
            SimpleTile(floorPlan.getTile(x, y))
        }
    })

}
