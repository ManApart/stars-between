package org.rak.microStars

import junit.framework.Assert.assertEquals
import org.junit.Test
import org.rak.microStars.Adjacency

class TileOrienterTest {


    @Test
    fun allOrientation() {
        val floorPlan = FloorPlan(3)
        val tile = floorPlan.getTile(1, 1)
        orient(tile, floorPlan)

        assertEquals(Adjacency.ALL, tile.adjacency)
    }

    @Test
    fun noneOrientation() {
        val floorPlan = FloorPlan(3)
        floorPlan.setTile(WALL, 1, 1)
        val tile = floorPlan.getTile(1, 1)
        orient(tile, floorPlan)

        assertEquals(Adjacency.NONE, tile.adjacency)
    }

    @Test
    fun oneSideOrientation() {
        val floorPlan = FloorPlan(3)
        floorPlan.setTile(WALL, 1, 1)
        val tile = floorPlan.getTile(1, 1)
        floorPlan.setTile(WALL, tile.position.down())
        orient(tile, floorPlan)

        assertEquals(Adjacency.ONE_SIDE, tile.adjacency)
        assertEquals(0, tile.rotation)
    }

    @Test
    fun oneSideOrientationRotated90() {
        val floorPlan = FloorPlan(3)
        floorPlan.setTile(WALL, 1, 1)
        val tile = floorPlan.getTile(1, 1)
        floorPlan.setTile(WALL, tile.position.left())
        orient(tile, floorPlan)

        assertEquals(Adjacency.ONE_SIDE, tile.adjacency)
        assertEquals(90, tile.rotation)
    }

    @Test
    fun twoSideOrientation() {
        val floorPlan = FloorPlan(3)
        floorPlan.setTile(WALL, 1, 1)
        val tile = floorPlan.getTile(1, 1)
        floorPlan.setTile(WALL, tile.position.left())
        floorPlan.setTile(WALL, tile.position.right())
        orient(tile, floorPlan)

        assertEquals(Adjacency.TWO_SIDE, tile.adjacency)
        assertEquals(0, tile.rotation)
    }

    @Test
    fun cornerOrientation() {
        val floorPlan = FloorPlan(3)
        floorPlan.setTile(WALL, 1, 1)
        val tile = floorPlan.getTile(1, 1)
        floorPlan.setTile(WALL, tile.position.left())
        floorPlan.setTile(WALL, tile.position.down())
        orient(tile, floorPlan)

        assertEquals(Adjacency.CORNER, tile.adjacency)
        assertEquals(0, tile.rotation)
    }

    @Test
    fun allCornerOrientation() {
        val floorPlan = FloorPlan(3)
        val tile = floorPlan.getTile(0, 0)
        orient(tile, floorPlan)

        assertEquals(Adjacency.CORNER, tile.adjacency)
    }

    @Test
    fun threeSideOrientation() {
        val floorPlan = FloorPlan(3)
        floorPlan.setTile(WALL, 1, 1)
        val tile = floorPlan.getTile(1, 1)
        floorPlan.setTile(WALL, tile.position.left())
        floorPlan.setTile(WALL, tile.position.down())
        floorPlan.setTile(WALL, tile.position.right())
        orient(tile, floorPlan)

        assertEquals(Adjacency.THREE_SIDE, tile.adjacency)
        assertEquals(0, tile.rotation)
    }

    @Test
    fun setTileOrientsNeighbors() {
        val floorPlan = FloorPlan(3)
        floorPlan.setTile(WALL, 1, 1)
        val tile = floorPlan.getTile(1, 1)
        floorPlan.setTile(WALL, tile.position.down())

        assertEquals(Adjacency.ONE_SIDE, tile.adjacency)
        assertEquals(Adjacency.ONE_SIDE, floorPlan.getTile(tile.position.down()).adjacency)
    }


}