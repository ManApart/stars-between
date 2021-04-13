package org.rak.microStars.views.shieldView

import org.rak.microStars.floorplan.FloorPlan
import org.rak.microStars.systems.Shield
import org.rak.microStars.views.View
import org.rak.microStars.views.overview.SimpleTile

class ShieldView(val tiles: List<List<SimpleTile>>, val shields: List<SimpleShield>) : View {
    constructor(floorPlan: FloorPlan) : this(buildSimpleTiles(floorPlan), buildShields(floorPlan))
}

private fun buildSimpleTiles(floorPlan: FloorPlan) = (0 until floorPlan.size).map { y ->
    (0 until floorPlan.size).map { x ->
        SimpleTile(floorPlan.getTile(x, y))
    }
}

private fun buildShields(floorPlan: FloorPlan): List<SimpleShield> {
    return floorPlan.getAllTiles().filter { it.system is Shield }.map { simpleShield(it) }
}