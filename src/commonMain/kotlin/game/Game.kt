package game

import airflow.simulateAir
import crew.CrewMan
import crew.Division
import floorplan.FloorPlan
import floorplan.Position
import power.simulatePower
import tile.FLOOR
import tile.SPACE
import tile.SystemType
import tile.WALL

object Game {
    var floorPlan = createFloorPlan(10)
    val crew = mutableMapOf<Int, CrewMan>()

    fun createFloorPlan(size: Int = 10): FloorPlan {
        val floorPlan = FloorPlan(size)
        val spaceTiles = floorPlan.getAllTiles().filter { it.isEdgeTile(floorPlan.size) }.map { it.position }
        val wallTiles = floorPlan.getAllTiles().map { it.position }.filter { isWallPosition(it, floorPlan.size) }
        val floorTiles =
            floorPlan.getAllTiles().map { it.position }.filter { !spaceTiles.contains(it) && !wallTiles.contains(it) }

        spaceTiles.forEach { floorPlan.setTile(SPACE, it) }
        wallTiles.forEach { floorPlan.setTile(WALL, it) }
        floorTiles.forEach { floorPlan.setTile(FLOOR, it) }
        return floorPlan
    }

    private fun isWallPosition(position: Position, size: Int): Boolean {
        return (position.x == 1 || position.x == size - 2 || position.y == 1 || position.y == size - 2) && position.x != 0 && position.y != 0 && position.x != size - 1 && position.y != size - 1
    }

    fun tick() {
        tickSystems(floorPlan)
        simulatePower(floorPlan)
        simulateAir(floorPlan)
    }

    private fun tickSystems(floorPlan: FloorPlan) {
        floorPlan.getAllTiles().forEach {
            it.system.tick(it)
        }
    }

    fun addCrewMan() {
        val id = (crew.keys.maxOrNull() ?: 0) + 1
        val tile = floorPlan.getAllTiles()
            .firstOrNull { it.system.type == SystemType.FLOOR && it.crewMan == null }
        if (tile != null) {
            crew[id] = CrewMan(id, Division.values().random(), tile)
            tile.crewMan = crew[id]
        } else {
            throw Exception("Could not find an open tile for the crewman")
        }
    }
}
