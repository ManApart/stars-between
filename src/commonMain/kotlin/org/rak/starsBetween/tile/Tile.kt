package org.rak.starsBetween.tile

import org.rak.starsBetween.airflow.Vent
import org.rak.starsBetween.crew.CrewMan
import org.rak.starsBetween.floorplan.Position
import org.rak.starsBetween.power.Engine
import org.rak.starsBetween.power.Wire
import org.rak.starsBetween.shipStructor.Floor
import org.rak.starsBetween.shipStructor.SPACE_SYSTEM
import org.rak.starsBetween.shipStructor.Wall
import org.rak.starsBetween.systems.shields.Shield
import org.rak.starsBetween.systems.ShipSystem

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
    var crewMan: CrewMan? = null
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
