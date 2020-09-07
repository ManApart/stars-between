package org.rak.microStars.tile

import org.rak.microStars.floorplan.FloorPlan

private val siblings = mapOf(
    TileType.ENGINE to listOf(TileType.ENGINE),
    TileType.FLOOR to listOf(TileType.FLOOR, TileType.WIRE_FLOOR),
    TileType.WIRE_FLOOR to listOf(TileType.WIRE_FLOOR, TileType.ENGINE, TileType.WIRE_WALL, TileType.VENT),
    TileType.SPACE to listOf(TileType.SPACE),
    TileType.VENT to listOf(TileType.VENT),
    TileType.WALL to listOf(TileType.WALL, TileType.WIRE_WALL),
    TileType.WIRE_WALL to listOf(TileType.WIRE_WALL, TileType.WALL),
    TileType.VOID to listOf(TileType.VOID)
)

fun orient(tile: Tile, floorPlan: FloorPlan) {
    val up = matches(tile.type, floorPlan.getTile(tile.position.up()).type)
    val down = matches(tile.type, floorPlan.getTile(tile.position.down()).type)
    val left = matches(tile.type, floorPlan.getTile(tile.position.left()).type)
    val right = matches(tile.type, floorPlan.getTile(tile.position.right()).type)
    val matchingNeighbors = listOf(up, down, left, right).count { it }

    tile.adjacency = when {
        matchingNeighbors == 4 -> Adjacency.ALL
        matchingNeighbors == 3 -> Adjacency.THREE_SIDE
        matchingNeighbors == 2 && isCorner(up, down, left, right) -> Adjacency.CORNER
        matchingNeighbors == 2 -> Adjacency.TWO_SIDE
        matchingNeighbors == 1 -> Adjacency.ONE_SIDE
        else -> Adjacency.NONE
    }

    tile.rotation = tile.adjacency.getRotation(up, down, left, right)

}

private fun matches(type: TileType, other: TileType): Boolean {
    return siblings.getOrDefault(type, listOf()).contains(other)
}

fun isCorner(up: Boolean, down: Boolean, left: Boolean, right: Boolean): Boolean {
    return (up && right) || (up && left) || (down && left) || (down && right)
}