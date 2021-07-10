package org.rak.starsBetween.views.shieldView

import org.rak.starsBetween.tile.Adjacency
import org.rak.starsBetween.tile.Tile
import org.rak.starsBetween.tile.SystemType

class SimpleTile(
    val type: SystemType,
    val x: Int,
    val y: Int,
    val adjacency: Adjacency,
    val rotation: Int,
    val id: Int,
    val shielded: Boolean
) {
    constructor(tile: Tile, id: Int, shielded: Boolean) : this(
        tile.system.type,
        tile.position.x,
        tile.position.y,
        tile.adjacency,
        tile.rotation,
        id,
        shielded
    )

}
