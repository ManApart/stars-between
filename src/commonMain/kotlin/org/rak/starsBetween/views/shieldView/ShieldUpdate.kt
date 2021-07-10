package org.rak.starsBetween.views.shieldView

import org.rak.starsBetween.systems.shields.Shield
import org.rak.starsBetween.tile.Tile
import java.lang.IllegalArgumentException

class ShieldUpdate(
    val id: Int,
    val power: Int,
    val radius: Int,

)

fun simpleShield(tile: Tile, id: Int): ShieldUpdate {
    if (tile.system !is Shield) {
        throw IllegalArgumentException("Simple Shield can only be constructed from Tiles that are Shields")
    }
    val shield = tile.system
    return ShieldUpdate(
        id,
        shield.power,
        shield.radius
    )
}
