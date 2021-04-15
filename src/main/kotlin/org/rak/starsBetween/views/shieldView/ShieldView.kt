package org.rak.starsBetween.views.shieldView

import org.rak.starsBetween.floorplan.FloorPlan
import org.rak.starsBetween.systems.Shield
import org.rak.starsBetween.views.View

class ShieldView(val tiles: List<List<SimpleTile>>, val shields: List<SimpleShield>) : View {
    constructor(floorPlan: FloorPlan) : this(buildSimpleTiles(floorPlan), buildShields(floorPlan))
}

private fun buildSimpleTiles(floorPlan: FloorPlan) = (0 until floorPlan.size).map { y ->
    var id = 1
    (0 until floorPlan.size).map { x ->
        val tile = floorPlan.getTile(x, y)
        val idToUse = if (tile.system is Shield){
            id++
        } else {
            0
        }
        SimpleTile(tile, idToUse)
    }
}

private fun buildShields(floorPlan: FloorPlan): List<SimpleShield> {
    var id = 1
    return floorPlan.getAllTiles().filter { it.system is Shield }.map { simpleShield(it, id++) }
}
