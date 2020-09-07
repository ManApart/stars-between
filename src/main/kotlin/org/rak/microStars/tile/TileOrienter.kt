package org.rak.microStars.tile

import org.rak.microStars.floorplan.FloorPlan

//TODO - pass a list of 'like tiles' and see if type is contained in them
fun orient(tile: Tile, floorPlan: FloorPlan){
    val up = floorPlan.getTile(tile.position.up()).type == tile.type
    val down = floorPlan.getTile(tile.position.down()).type == tile.type
    val left = floorPlan.getTile(tile.position.left()).type == tile.type
    val right = floorPlan.getTile(tile.position.right()).type == tile.type
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