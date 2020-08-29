package org.rak.microStars

import junit.framework.Assert.assertEquals
import org.junit.Test
import org.rak.microStars.floorplan.FloorPlan
import org.rak.microStars.floorplan.createDistancesFrom

class DistanceBuilderTest {

    @Test
    fun simpleDistances() {
        val floorPlan = FloorPlan(3)
        val source = floorPlan.getTile(0, 1)
        val distanceMap = createDistancesFrom(source, floorPlan)

        val expected = arrayOf(
            arrayOf(1, 2, 3),
            arrayOf(0, 1, 2),
            arrayOf(1, 2, 3)
        ).toMap().transpose()

        assertEquals(expected, distanceMap.costs)
    }



}