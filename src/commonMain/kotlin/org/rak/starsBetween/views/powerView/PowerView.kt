package org.rak.starsBetween.views.powerView

import org.rak.starsBetween.floorplan.FloorPlan
import org.rak.starsBetween.views.View

class PowerView(val tiles: List<List<PowerViewTile>>) : View {

    constructor(floorPlan: FloorPlan) : this((0 until floorPlan.size).map { y ->
        (0 until floorPlan.size).map { x ->
            PowerViewTile(floorPlan.getTile(x, y))
        }
    })

}
