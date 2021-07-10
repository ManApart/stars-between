package org.rak.starsBetween

import org.junit.Assert.assertEquals
import org.junit.Test
import org.rak.starsBetween.floorplan.Position

class AreaTest {

    @Test
    fun findRoute() {
        val plan = listOf(
            listOf(0, 0, 0),
            listOf(0, 2, 0),
            listOf(2, 2, 0)
        )

        val floorPlan = createFloorPlanWithTiles(plan)
        val area = floorPlan.airAreas.areas.first()
        val source = floorPlan.getTile(0, 1)
        val route = area.findRoute(source, floorPlan.getTile(2,2))

        val expected = listOf(
            Position(0, 0),
            Position(1, 0),
            Position(2, 0),
            Position(2, 1),
            Position(2, 2)
        )

        assertEquals(expected, route.map { it.position })
    }

    @Test
    fun findRouteWithNoMatch() {
        val plan = listOf(
            listOf(1, 1, 2),
            listOf(1, 2, 0),
            listOf(2, 2, 0)
        )

        val floorPlan = createFloorPlanWithTiles(plan)
        val area = floorPlan.airAreas.areas.first()
        val source = floorPlan.getTile(0, 1)
        val route = area.findRoute(source, floorPlan.getTile(2,2))

        assertEquals(0, route.size)
    }

}
