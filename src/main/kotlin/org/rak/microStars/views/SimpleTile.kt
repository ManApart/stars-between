package org.rak.microStars.views

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

//    fun fromSimpleTile(simpleTile: SimpleTile): Tile {
//        val tile = tileTypes
//            .first { it.type == simpleTile.type }
//            .copy(position = Position(simpleTile.x, simpleTile.y), solid = simpleTile.solid)
//
//        tile.health = simpleTile.health
//        tile.power = simpleTile.power
//        tile.air = simpleTile.air
//        tile.adjacency = simpleTile.adjacency
//        tile.rotation = simpleTile.rotation
//
//        return tile
//    }
}
