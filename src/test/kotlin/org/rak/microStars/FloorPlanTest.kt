package org.rak.microStars

import junit.framework.Assert.assertEquals
import org.junit.Test
import org.rak.microStars.floorplan.FloorPlan
import org.rak.microStars.floorplan.Position

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
}