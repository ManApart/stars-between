package org.rak.microStars

import junit.framework.Assert.*
import org.junit.Test
import org.rak.microStars.floorplan.FloorPlan
import org.rak.microStars.floorplan.Position
import org.rak.microStars.floorplan.createDistancesFrom
import org.rak.microStars.tile.SPACE
import org.rak.microStars.tile.Tile
import org.rak.microStars.tile.TileType
import org.rak.microStars.tile.WALL

//TODO - take area instead of floorplan
class DistanceBuilderTest {

    @Test
    fun setupTest() {
        val plan = listOf(
            listOf(0, 0, 0),
            listOf(0, 2, 0),
            listOf(2, 2, 0)
        )

        val floorPlan = createFloorPlanWithTiles(plan)

        val spacePositions = listOf(
            Position(0, 0),
            Position(0, 1),
            Position(1, 0),
            Position(2, 0),
            Position(2, 1),
            Position(2, 2)
        )
        val wallPositions = listOf(
            Position(0, 2),
            Position(1, 1),
            Position(1, 2)
        )

        assertTrue(spacePositions.all {
            floorPlan.getTile(it).type == TileType.SPACE
        })

        assertTrue(wallPositions.all {
            floorPlan.getTile(it).type == TileType.WALL
        })
    }

    @Test
    fun simpleDistances() {
        val floorPlan = FloorPlan(3)
        val source = floorPlan.getTile(0, 1)
        val distanceMap = createDistancesFrom(source, floorPlan.airAreas.areas.first())

        val expected = listOf(
            listOf(1, 2, 3),
            listOf(0, 1, 2),
            listOf(1, 2, 3)
        ).toMap().transpose()

        assertEquals(expected, distanceMap.costs)
    }

    @Test
    fun distancesOnlyTrackNonSolidTiles() {
        val plan = listOf(
            listOf(0, 0, 0),
            listOf(0, 2, 0),
            listOf(2, 2, 0)
        )

        val floorPlan = createFloorPlanWithTiles(plan)
        val source = floorPlan.getTile(0, 1)
        val distanceMap = createDistancesFrom(source, floorPlan.airAreas.areas.first())

        val expected = listOf(
            listOf(1, 2, 3),
            listOf(0, -1, 4),
            listOf(-1, -1, 5)
        ).toMap().transpose().filterNonEntries()

        assertEquals(expected, distanceMap.costs)
    }

    @Test
    fun nearestTileOfType() {
        val plan = listOf(
            listOf(1, 0, 0),
            listOf(1, 2, 0),
            listOf(2, 2, 0)
        )

        val floorPlan = createFloorPlanWithTiles(plan)
        val area = floorPlan.airAreas.areas.first()
        val source = floorPlan.getTile(0, 1)
        val distanceMap = createDistancesFrom(source, area)
        val nearestSpace = distanceMap.getNearestTileOfType(TileType.SPACE, area)
        val expected = floorPlan.getTile(1, 0)

        assertEquals(expected, nearestSpace)
    }

    @Test
    fun nearestTileOfTypeWithNoMatch() {
        val plan = listOf(
            listOf(1, 1, 2),
            listOf(1, 2, 0),
            listOf(2, 2, 0)
        )

        val floorPlan = createFloorPlanWithTiles(plan)
        val area = floorPlan.airAreas.areas.first()
        val source = floorPlan.getTile(0, 1)
        val distanceMap = createDistancesFrom(source, area)
        val nearestSpace = distanceMap.getNearestTileOfType(TileType.SPACE, area)

        assertNull(nearestSpace)
    }


    private fun Map<Int, Map<Int, Int>>.filterNonEntries(): Map<Int, Map<Int, Int>> {
        return entries.map { outer ->
            val filteredEntries = outer.value.entries.filter { it.value != -1 }.map { it.key to it.value }.toMap()
            outer.key to filteredEntries
        }.toMap()
    }


}