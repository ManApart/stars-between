package tile

import airflow.Vent
import crew.CrewMan
import floorplan.Position
import power.Engine
import power.Wire
import shipStructor.Floor
import shipStructor.SPACE_SYSTEM
import shipStructor.Wall
import systems.shields.Shield
import systems.ShipSystem

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

fun getDefault(type: SystemType): Tile {
    return defaultTiles.firstOrNull{ it.system.type == type } ?: DEFAULT_TILE
}

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

    override fun toString(): String {
        return "$position, air: $air, system: $system"
    }
}
