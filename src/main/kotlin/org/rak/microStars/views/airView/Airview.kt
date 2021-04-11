package org.rak.microStars.views.airView

import org.rak.microStars.floorplan.FloorPlan
import org.rak.microStars.views.View
import org.rak.microStars.views.powerView.PowerViewTile

class Airview(val tiles: List<List<AirViewTile>>) : View {

    constructor(floorPlan: FloorPlan) : this((0 until floorPlan.size).map { y ->
        (0 until floorPlan.size).map { x ->
            AirViewTile(floorPlan.getTile(x, y))
        }
    })

}
