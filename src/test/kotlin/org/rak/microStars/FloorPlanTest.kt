package org.rak.microStars

import junit.framework.Assert
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.rak.microStars.floorplan.FloorPlan
import org.rak.microStars.floorplan.Position
import org.rak.microStars.floorplan.createDistancesFrom
import org.rak.microStars.tile.SPACE

class FloorPlanTest {

    @Test
    fun getNeighbors(){
        val floorPlan = FloorPlan(3)
        val neighbors = floorPlan.getNeighbors(floorPlan.getTile(1,1))
        val expectedNeighbors = listOf(
            Position(1, 0),
            Position(1, 2),
            Position(0, 1),
            Position(2, 1)
        ).map { floorPlan.getTile(it) }
        assertEquals(expectedNeighbors, neighbors)
    }

    @Test
    fun getNeighborsOnEdge(){
        val floorPlan = FloorPlan(3)
        val neighbors = floorPlan.getNeighbors(floorPlan.getTile(0,0))
        val expectedNeighbors = listOf(
            Position(0, 1),
            Position(1, 0)
        ).map { floorPlan.getTile(it) }
        assertEquals(expectedNeighbors, neighbors)
    }

    @Test
    fun findRoute() {
        val plan = listOf(
            listOf(0, 0, 0),
            listOf(0, 2, 0),
            listOf(2, 2, 0)
        )

        val floorPlan = createFloorPlanWithTiles(plan)
        floorPlan.buildDistanceMaps()
        val source = floorPlan.getTile(0, 1)
        val route = floorPlan.findRoute(source, floorPlan.getTile(2,2))

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
        floorPlan.buildDistanceMaps()
        val source = floorPlan.getTile(0, 1)
        val route = floorPlan.findRoute(source, floorPlan.getTile(2,2))

        assertEquals(0, route.size)
    }


}