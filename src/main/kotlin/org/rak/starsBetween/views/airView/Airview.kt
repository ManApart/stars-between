package org.rak.starsBetween.views.airView

import org.rak.starsBetween.floorplan.FloorPlan
import org.rak.starsBetween.views.View

class Airview(val tiles: List<List<AirViewTile>>) : View {

    constructor(floorPlan: FloorPlan) : this((0 until floorPlan.size).map { y ->
        (0 until floorPlan.size).map { x ->
            AirViewTile(floorPlan.getTile(x, y))
        }
    })

}
