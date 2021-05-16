package org.rak.starsBetween.views.crewView

import org.rak.starsBetween.crew.CrewMan
import org.rak.starsBetween.floorplan.FloorPlan
import org.rak.starsBetween.views.View

class CrewView(val tiles: List<List<SimpleTile>>, val shields: List<CrewManUpdate>) : View {
}

fun createCrewView(floorPlan: FloorPlan, crew: Map<Int, CrewMan>): CrewView {
    val tiles = (0 until floorPlan.size).map { y ->
        (0 until floorPlan.size).map { x ->
            val tile = floorPlan.getTile(x, y)
            SimpleTile(tile)
        }
    }
    val crewUpdates = crew.values.map { CrewManUpdate(it) }
    return CrewView(tiles, crewUpdates)
}
