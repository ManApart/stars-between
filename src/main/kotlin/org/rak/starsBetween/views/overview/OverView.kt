package org.rak.starsBetween.views.overview

import org.rak.starsBetween.floorplan.FloorPlan
import org.rak.starsBetween.views.View

class OverView(val tiles: List<List<SimpleTile>>) : View {

    constructor(floorPlan: FloorPlan) : this((0 until floorPlan.size).map { y ->
        (0 until floorPlan.size).map { x ->
            SimpleTile(floorPlan.getTile(x, y))
        }
    })

}
