package org.rak.microStars

import org.junit.Assert.assertEquals
import org.junit.Test
import org.rak.microStars.airflow.pushAir
import org.rak.microStars.floorplan.FloorPlan
import org.rak.microStars.tile.FLOOR
import org.rak.microStars.tile.SPACE

class AirSimulatorTest {

    @Test
    fun setupTest() {
        val floorPlan = createFloorPlanWithAir(
            listOf(
                listOf(0, 0, 0),
                listOf(0, 100, 0),
                listOf(0, 0, 0)
            )
        )

        val expected = listOf(
            listOf(0, 0, 0),
            listOf(0, 100, 0),
            listOf(0, 0, 0)
        )

        val airMap = floorPlan.getTileMap().map { row -> row.value.map { column -> column.value.air } }
        assertEquals(expected, airMap)
    }

    @Test
    fun simpleEvenDistribution() {
        val floorPlan = createFloorPlanWithAir(
            listOf(
                listOf(0, 0, 0),
                listOf(0, 100, 0),
                listOf(0, 0, 0)
            )
        )

        val expected = listOf(
            listOf(0, 20, 0),
            listOf(20, 20, 20),
            listOf(0, 20, 0)
        )

        val tile = floorPlan.getTile(1, 1)
        val neighbors = floorPlan.getNeighbors(tile).filter { !it.system.isSolid() && it.air != 100 }
        pushAir(tile, neighbors)

        val airMap = floorPlan.getTileMap().map { row -> row.value.map { column -> column.value.air } }
        assertEquals(expected, airMap)
    }

    @Test
    fun simpleUnevenDistribution() {
        val floorPlan = createFloorPlanWithAir(
            listOf(
                listOf(0, 0, 0),
                listOf(0, 100, 100),
                listOf(0, 0, 0)
            )
        )

        val expected = listOf(
            listOf(0, 25, 0),
            listOf(25, 25, 100),
            listOf(0, 25, 0)
        )

        val tile = floorPlan.getTile(1, 1)
        val neighbors = floorPlan.getNeighbors(tile).filter { !it.system.isSolid() && it.air != 100 }
        pushAir(tile, neighbors)

        val airMap = floorPlan.getTileMap().map { row -> row.value.map { column -> column.value.air } }
        assertEquals(expected, airMap)
    }

    @Test
    fun remainder() {
        val floorPlan = createFloorPlanWithAir(
            listOf(
                listOf(0, 0, 0),
                listOf(0, 51, 0),
                listOf(0, 0, 0)
            )
        )

        val expected = listOf(
            listOf(0, 10, 0),
            listOf(10, 11, 10),
            listOf(0, 10, 0)
        )

        val tile = floorPlan.getTile(1, 1)
        val neighbors = floorPlan.getNeighbors(tile).filter { !it.system.isSolid() && it.air != 100 }
        pushAir(tile, neighbors)

        val airMap = floorPlan.getTileMap().map { row -> row.value.map { column -> column.value.air } }
        assertEquals(expected, airMap)
    }

    @Test
    fun remainderDistributed() {
        val floorPlan = createFloorPlanWithAir(
            listOf(
                listOf(0, 0, 0),
                listOf(0, 52, 0),
                listOf(0, 0, 0)
            )
        )

        val expected = listOf(
            listOf(0, 10, 0),
            listOf(10, 11, 11),
            listOf(0, 10, 0)
        )

        val tile = floorPlan.getTile(1, 1)
        val neighbors = floorPlan.getNeighbors(tile).filter { !it.system.isSolid() && it.air != 100 }
        pushAir(tile, neighbors)

        val airMap = floorPlan.getTileMap().map { row -> row.value.map { column -> column.value.air } }
        assertEquals(expected, airMap)
    }

    @Test
    fun remainderPrefersSpaceTiles() {
        val floorPlan = createFloorPlanWithAir(
            listOf(
                listOf(0, 0, 0),
                listOf(0, 51, 0),
                listOf(0, 0, 0)
            )
        )

        val expected = listOf(
            listOf(0, 10, 0),
            listOf(10, 10, 11),
            listOf(0, 10, 0)
        )

        //Tile to the right is a space tile
        floorPlan.setTileWithoutUpdates(SPACE, 2, 1)

        val tile = floorPlan.getTile(1, 1)
        val spaceTile = floorPlan.getTile(2, 1)
        spaceTile.air = 0

        val neighbors = floorPlan.getNeighbors(tile).filter { !it.system.isSolid() && it.air != 100 }
        pushAir(tile, neighbors)

        val airMap = floorPlan.getTileMap().map { row -> row.value.map { column -> column.value.air } }
        assertEquals(expected, airMap)
    }


    private fun createFloorPlanWithAir(plan: List<List<Int>>): FloorPlan {
        val floorPlan = FloorPlan(plan.size)
        plan.toMap().entries.forEach { (y, entry) ->
            entry.entries.forEach { (x, air) ->
                floorPlan.setTile(FLOOR.copy(), x, y)
                floorPlan.getTile(x, y).air = air
            }
        }
        return floorPlan
    }


}
