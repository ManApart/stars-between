package org.rak.microStars.views

import org.rak.microStars.floorplan.FloorPlan

class SimpleFloorPlan(val tiles: List<List<SimpleTile>>) {

    constructor(floorPlan: FloorPlan) : this((0 until floorPlan.size).map { y ->
        (0 until floorPlan.size).map { x ->
            SimpleTile(floorPlan.getTile(x, y))
        }
    })

    //fun fromSimpleFloorPlan(simpleFloorPlan: SimpleFloorPlan): FloorPlan {
//    val size = (simpleFloorPlan.tiles.flatten().maxByOrNull { it.x }?.x ?: 9) + 1
//    val floorPlan = FloorPlan(size)
//    simpleFloorPlan.tiles.flatten().map {
//        floorPlan.setTileWithoutUpdates(fromSimpleTile(it), it.x, it.y)
//    }
//    floorPlan.getAllTiles().forEach { orient(it, floorPlan) }
//    floorPlan.updateAreas()
//
//    return floorPlan
//}

}
