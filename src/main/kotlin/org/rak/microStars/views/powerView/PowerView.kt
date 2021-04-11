package org.rak.microStars.views.powerView

import org.rak.microStars.floorplan.FloorPlan
import org.rak.microStars.views.View

class PowerView(val tiles: List<List<PowerViewTile>>) : View {

    constructor(floorPlan: FloorPlan) : this((0 until floorPlan.size).map { y ->
        (0 until floorPlan.size).map { x ->
            PowerViewTile(floorPlan.getTile(x, y))
        }
    })

}
