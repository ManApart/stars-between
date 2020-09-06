package org.rak.microStars.tile

import org.rak.microStars.floorplan.Position

val DEFAULT_TILE = Tile(TileType.VOID)
val ENGINE = Tile(TileType.ENGINE)
val FLOOR = Tile(TileType.FLOOR)
val SPACE = Tile(TileType.SPACE, airProduced = -10)
val VENT = Tile(TileType.VENT, airProduced = 10)
val WALL = Tile(TileType.WALL, solid = true)
val WIRE_FLOOR = Tile(TileType.WIRE_FLOOR)
val WIRE_WALL = Tile(TileType.WIRE_WALL, solid = true)

val tileTypes = listOf(
    ENGINE,
    FLOOR,
    SPACE,
    VENT,
    WALL,
    WIRE_FLOOR,
    WIRE_WALL
)

data class Tile(
    val type: TileType,
    val position: Position = Position(),
    private val totalHealth: Int = 100,
    private val solid: Boolean = false,
    val airProduced: Int = 0
) {

    var health = totalHealth
    var air = if (solid) {
        0
    } else {
        100
    }
    var adjacency = Adjacency.NONE
    var rotation = 0
    var distanceMap = DistanceMap(this)
    var distanceFromSelected = 0

    fun isSolid(): Boolean {
        return solid && health > 0
    }

    fun isEdgeTile(floorPlanSize: Int): Boolean {
        return position.x == 0 || position.y == 0 || position.x == floorPlanSize - 1 || position.y == floorPlanSize - 1
    }
}

fun fromSimpleTile(simpleTile: SimpleTile): Tile {
    val tile = tileTypes
        .first { it.type == simpleTile.type }
        .copy(position = Position(simpleTile.x, simpleTile.y), solid = simpleTile.solid)

    tile.health = simpleTile.health
    tile.air = simpleTile.air
    tile.adjacency = simpleTile.adjacency
    tile.rotation = simpleTile.rotation

    return tile
}