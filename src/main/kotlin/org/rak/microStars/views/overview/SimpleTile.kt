package org.rak.microStars.views.overview

import org.rak.microStars.tile.Adjacency
import org.rak.microStars.tile.Tile
import org.rak.microStars.tile.SystemType

class SimpleTile(
    val type: SystemType,
    val x: Int,
    val y: Int,
    val adjacency: Adjacency,
    val rotation: Int,
    val distance: Int
) {
    constructor(tile: Tile) : this(
        tile.system.type,
        tile.position.x,
        tile.position.y,
        tile.adjacency,
        tile.rotation,
        tile.distanceFromSelected
    )

}
