package org.rak.microStars.views.persistence

import org.rak.microStars.floorplan.Position
import org.rak.microStars.tile.Adjacency
import org.rak.microStars.tile.SystemType
import org.rak.microStars.tile.Tile

class PersistedTile(
    val type: SystemType,
    val system: PersistedSystem,
    val x: Int,
    val y: Int,
    val air: Int,
    val adjacency: Adjacency,
    val rotation: Int,
    val distance: Int
) {
    constructor(tile: Tile) : this(
        tile.system.type,
        tile.system.persisted(),
        tile.position.x,
        tile.position.y,
        tile.air,
        tile.adjacency,
        tile.rotation,
        tile.distanceFromSelected
    )

    fun toTile(): Tile {
        val tile = Tile(Position(x, y), system.toSystem())
        tile.air = air
        tile.adjacency = adjacency
        tile.rotation = rotation
        return tile
    }
}
