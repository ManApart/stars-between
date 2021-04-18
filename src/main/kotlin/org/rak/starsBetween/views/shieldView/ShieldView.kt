package org.rak.starsBetween.views.shieldView

import org.rak.starsBetween.floorplan.FloorPlan
import org.rak.starsBetween.systems.Shield
import org.rak.starsBetween.views.View

class ShieldView(val tiles: List<List<SimpleTile>>, val shields: List<SimpleShield>) : View {
}

fun FloorPlan.toShieldView(): ShieldView {
    val shields = mutableListOf<SimpleShield>()
    var id = 0

    val tiles = (0 until size).map { y ->
        (0 until size).map { x ->
            val tile = getTile(x, y)
            if (tile.system is Shield) {
                id++
                shields.add(simpleShield(tile, id))
            }

            val idToUse = if (tile.system is Shield) {
                id
            } else {
                0
            }
            SimpleTile(tile, idToUse)
        }
    }

    return ShieldView(tiles, shields)
}
