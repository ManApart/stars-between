package org.rak.microStars.tile

import org.rak.microStars.floorplan.FloorPlan

private val siblings = mapOf(
    SystemType.ENGINE to listOf(SystemType.ENGINE),
    SystemType.FLOOR to listOf(SystemType.FLOOR, SystemType.WIRE_FLOOR),
    SystemType.WIRE_FLOOR to listOf(SystemType.WIRE_FLOOR, SystemType.ENGINE, SystemType.WIRE_WALL, SystemType.VENT),
    SystemType.SHIELD to listOf(SystemType.SHIELD),
    SystemType.SPACE to listOf(SystemType.SPACE),
    SystemType.VENT to listOf(SystemType.VENT),
    SystemType.WALL to listOf(SystemType.WALL, SystemType.WIRE_WALL),
    SystemType.WIRE_WALL to listOf(SystemType.WIRE_WALL, SystemType.WALL),
    SystemType.VOID to listOf(SystemType.VOID)
)

fun orient(tile: Tile, floorPlan: FloorPlan) {
    val up = matches(tile.system.type, floorPlan.getTile(tile.position.up()).system.type)
    val down = matches(tile.system.type, floorPlan.getTile(tile.position.down()).system.type)
    val left = matches(tile.system.type, floorPlan.getTile(tile.position.left()).system.type)
    val right = matches(tile.system.type, floorPlan.getTile(tile.position.right()).system.type)
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

private fun matches(type: SystemType, other: SystemType): Boolean {
    return siblings.getOrDefault(type, listOf()).contains(other)
}

fun isCorner(up: Boolean, down: Boolean, left: Boolean, right: Boolean): Boolean {
    return (up && right) || (up && left) || (down && left) || (down && right)
}
