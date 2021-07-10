package org.rak.starsBetween.views.powerView

import org.rak.starsBetween.power.Engine
import org.rak.starsBetween.power.Powerable
import org.rak.starsBetween.tile.Adjacency
import org.rak.starsBetween.tile.Tile
import org.rak.starsBetween.tile.SystemType

class PowerViewTile(
    val type: SystemType,
    val x: Int,
    val y: Int,
    val adjacency: Adjacency,
    val rotation: Int,
    val distance: Int,
    val power: Int
) {
    constructor(tile: Tile) : this(
        tile.system.type,
        tile.position.x,
        tile.position.y,
        tile.adjacency,
        tile.rotation,
        tile.distanceFromSelected,
        tile.getPower()
    )

}

private fun Tile.getPower(): Int {
    return when (system) {
        is Powerable -> system.power
        is Engine -> system.power
        else -> 0
    }
}
