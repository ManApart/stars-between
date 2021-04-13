package org.rak.microStars.tile

import org.rak.microStars.airflow.Vent
import org.rak.microStars.floorplan.Position
import org.rak.microStars.power.Engine
import org.rak.microStars.power.Wire
import org.rak.microStars.shipStructor.Floor
import org.rak.microStars.shipStructor.SPACE_SYSTEM
import org.rak.microStars.shipStructor.Wall
import org.rak.microStars.systems.Shield
import org.rak.microStars.systems.ShipSystem

val DEFAULT_TILE = Tile(Position(), SPACE_SYSTEM)
val SPACE = Tile(Position(), SPACE_SYSTEM)
val WALL = Tile(Position(), Wall())
val FLOOR = Tile(Position(), Floor())

val defaultTiles = listOf(
    SPACE,
    WALL,
    FLOOR,
    Tile(Position(), Shield()),
    Tile(Position(), Vent()),
    Tile(Position(), Engine()),
    Tile(Position(), Wire("Floor Wire", SystemType.WIRE_FLOOR)),
    Tile(Position(), Wire("Wall Wire", SystemType.WIRE_WALL)),
)

data class Tile(
    val position: Position = Position(),
    val system: ShipSystem
) {

    var air = if (system.isSolid()) {
        0
    } else {
        100
    }
    var adjacency = Adjacency.NONE
    var rotation = 0
    var distanceMap = DistanceMap(this)
    var distanceFromSelected = 0

    fun isEdgeTile(floorPlanSize: Int): Boolean {
        return position.x == 0 || position.y == 0 || position.x == floorPlanSize - 1 || position.y == floorPlanSize - 1
    }
}
