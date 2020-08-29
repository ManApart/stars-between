package org.rak.microStars.tile

import org.rak.microStars.tile.Tile

class SimpleTile(tile: Tile) {
    val name = tile.name
    val x = tile.position.x
    val y = tile.position.y
    val solid = tile.isSolid()
    val air = tile.air
    val health = tile.health
    val adjacency = tile.adjacency
    val rotation = tile.rotation
}