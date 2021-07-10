package org.rak.starsBetween.views.shieldView

import org.rak.starsBetween.floorplan.FloorPlan
import org.rak.starsBetween.systems.shields.Shield
import org.rak.starsBetween.tile.SystemType
import org.rak.starsBetween.views.View

class ShieldView(val tiles: List<List<SimpleTile>>, val shields: List<ShieldUpdate>) : View {
}

fun FloorPlan.toShieldView(): ShieldView {
    val shieldedTiles = getSystems(SystemType.SHIELD).values.flatMap { (it.system as Shield).getProtectedTiles() }.toSet()
    val tiles = (0 until size).map { y ->
        (0 until size).map { x ->
            val tile = getTile(x, y)

            val idToUse = if (tile.system is Shield) {
                getId(tile.system)
            } else {
                0
            }

            val shielded = shieldedTiles.contains(tile)

            SimpleTile(tile, idToUse, shielded)
        }
    }
    val shields = getSystems(SystemType.SHIELD).entries.map { (id, shield) -> simpleShield(shield, id) }
    return ShieldView(tiles, shields)
}
