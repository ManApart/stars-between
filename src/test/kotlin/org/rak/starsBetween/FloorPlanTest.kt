package org.rak.starsBetween

import junit.framework.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.rak.starsBetween.floorplan.FloorPlan
import org.rak.starsBetween.floorplan.Position

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
    fun getNeighborsR2(){
        val floorPlan = FloorPlan(10)
        val neighbors = floorPlan.getNeighbors(floorPlan.getTile(4,4), 2)

        assertEquals(12, neighbors.size)

        assertTrue(neighbors.containsAll(listOf(
            floorPlan.getTile(4, 6),
            floorPlan.getTile(4, 2),
            floorPlan.getTile(6, 4),
            floorPlan.getTile(2, 4),
            floorPlan.getTile(5, 5)
        )))
    }

}
