package org.rak.microStars.tile

import org.rak.microStars.floorplan.FloorPlan

fun orient(tile: Tile, floorPlan: FloorPlan){
    val up = floorPlan.getTile(tile.position.up()).isType(tile)
    val down = floorPlan.getTile(tile.position.down()).isType(tile)
    val left = floorPlan.getTile(tile.position.left()).isType(tile)
    val right = floorPlan.getTile(tile.position.right()).isType(tile)
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

fun isCorner(up: Boolean, down: Boolean, left: Boolean, right: Boolean) : Boolean {
    return (up && right) || (up && left) || (down && left) || (down && right)
}