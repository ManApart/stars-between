package floorplan

import airflow.simulateAir
import crew.CrewMan
import crew.Division
import power.simulatePower
import tile.FLOOR
import tile.SPACE
import tile.SystemType
import tile.WALL

class Ship(
    val floorPlan: FloorPlan = createFloorPlan(10),
    val crew: MutableMap<Int, CrewMan> = mutableMapOf<Int, CrewMan>()
) {
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

    fun addCrewMan(): CrewMan {
        val id = (crew.keys.maxOrNull() ?: 0) + 1
        val tile = floorPlan.getAllTiles()
            .firstOrNull { it.system.type == SystemType.FLOOR && it.crewMan == null }
            ?: throw Exception("Could not find an open tile for the crewman")

        val man = CrewMan(id, Division.values().random(), tile)
        crew[id] = man
        tile.crewMan = crew[id]
        return man
    }

    fun removeCrewMan(id: Int) {
        val man = crew[id]
        if (man != null) {
            crew.remove(id)
            man.tile.crewMan = null
        }
    }
}

fun createFloorPlan(size: Int = 10): FloorPlan {
    val floorPlan = FloorPlan(size)
    val spaceTiles = floorPlan.getAllTiles().filter { it.isEdgeTile(floorPlan.size) }.map { it.position }
    val wallTiles = floorPlan.getAllTiles().map { it.position }.filter { isWallPosition(it, floorPlan.size) }
    val floorTiles =
        floorPlan.getAllTiles().map { it.position }.filter { !spaceTiles.contains(it) && !wallTiles.contains(it) }

    spaceTiles.forEach { floorPlan.setTileWithoutUpdates(SPACE, it) }
    wallTiles.forEach { floorPlan.setTileWithoutUpdates(WALL, it) }
    floorTiles.forEach { floorPlan.setTileWithoutUpdates(FLOOR, it) }
    floorPlan.setTile(SPACE, Position(0, 0))
    return floorPlan
}

private fun isWallPosition(position: Position, size: Int): Boolean {
    return (position.x == 1 || position.x == size - 2 || position.y == 1 || position.y == size - 2) && position.x != 0 && position.y != 0 && position.x != size - 1 && position.y != size - 1
}