package org.rak.microStars.tile

class SimpleTile(
    val type: TileType,
    val x: Int,
    val y: Int,
    val solid: Boolean,
    val air: Int,
    val health: Int,
    val power: Int,
    val adjacency: Adjacency,
    val rotation: Int,
    val distance: Int = 0
) {
    constructor(tile: Tile) : this(
        tile.type,
        tile.position.x,
        tile.position.y,
        tile.isSolid(),
        tile.air,
        tile.health,
        tile.power,
        tile.adjacency,
        tile.rotation,
        tile.distanceFromSelected
    )
}