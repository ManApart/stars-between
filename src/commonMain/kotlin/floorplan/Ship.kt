package floorplan

import airflow.simulateAir
import com.soywiz.kds.iterators.fastForEach
import crew.CrewMan
import crew.Division
import power.simulatePower
import tile.*

class Ship(
    val floorPlan: FloorPlan = createFloorPlan(10),
    val crew: MutableMap<Int, CrewMan> = mutableMapOf()
) {
    fun tick() {
        tickSystems(floorPlan)
        simulatePower(floorPlan)
        simulateAir(floorPlan)
        tickCrew(crew.values.toList())
    }

    private fun tickSystems(floorPlan: FloorPlan) {
        floorPlan.getAllTiles().forEach {
            it.system.tick(it)
        }
    }

    private fun tickCrew(crew: List<CrewMan>) {
        crew.fastForEach { it.tick() }
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
    floorPlan.doFullSetup()
    return floorPlan
}

private fun isWallPosition(position: Position, size: Int): Boolean {
    return (position.x == 1 || position.x == size - 2 || position.y == 1 || position.y == size - 2) && position.x != 0 && position.y != 0 && position.x != size - 1 && position.y != size - 1
}