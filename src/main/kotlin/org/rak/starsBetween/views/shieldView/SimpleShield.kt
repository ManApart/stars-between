package org.rak.starsBetween.views.shieldView

import org.rak.starsBetween.systems.Shield
import org.rak.starsBetween.tile.Tile
import java.lang.IllegalArgumentException

class SimpleShield(
    val x: Int,
    val y: Int,
    val id: Int,
    val frequency: Int,
    val power: Int,
    val totalPowerCapacity: Int,
    val currentPowerPull: Int,
    val radius: Int,

)

fun simpleShield(tile: Tile, id: Int): SimpleShield {
    if (tile.system !is Shield) {
        throw IllegalArgumentException("Simple Shield can only be constructed from Tiles that are not Shields")
    }
    val shield = tile.system
    return SimpleShield(
        tile.position.x,
        tile.position.y,
        id,
        shield.frequency,
        shield.power,
        shield.totalPowerCapacity,
        shield.currentPowerPull,
        shield.radius
    )
}
