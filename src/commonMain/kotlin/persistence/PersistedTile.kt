package persistence

import floorplan.Position
import kotlinx.serialization.Serializable
import tile.Adjacency
import tile.SystemType
import tile.Tile

@Serializable
data class PersistedTile(
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
