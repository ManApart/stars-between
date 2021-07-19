package persistence

import crew.CrewMan
import crew.Division
import floorplan.FloorPlan
import kotlinx.serialization.Serializable

@Serializable
class PersistedCrewMan(
    val division: Division,
    val x: Int,
    val y: Int
) {
    constructor(man: CrewMan): this(man.division, man.tile.position.x, man.tile.position.x)

    fun toCrewMan(id: Int, floorPlan: FloorPlan): CrewMan {
        val tile = floorPlan.getTile(x, y)
        return CrewMan(id, division, tile).also { tile.crewMan = it }
    }
}