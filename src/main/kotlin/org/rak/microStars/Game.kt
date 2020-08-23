package org.rak.microStars

object Game {
    var floorPlan = createFloorPlan(10)

    fun createFloorPlan(size: Int = 10) : FloorPlan{
        val floorPlan = FloorPlan(size)
        val spaceTiles = floorPlan.getAllTiles().filter { it.isEdgeTile(floorPlan.size) }.map { it.position }
        val wallTiles = floorPlan.getAllTiles().map { it.position }.filter { isWallPosition(it, floorPlan.size) }
        val floorTiles =
            floorPlan.getAllTiles().map { it.position }.filter { !spaceTiles.contains(it) && !wallTiles.contains(it) }

        spaceTiles.forEach { floorPlan.setTile(SPACE, it) }
        wallTiles.forEach { floorPlan.setTile(WALL, it) }
        floorTiles.forEach { floorPlan.setTile(FLOOR, it) }
        return floorPlan
    }


    private fun isWallPosition(position: Position, size: Int) : Boolean{
        return (position.x == 1 || position.x == size-2 || position.y == 1 || position.y == size-2) && position.x != 0 && position.y != 0 && position.x != size-1 && position.y != size-1
    }

    fun tick() {
        val tickPosition = Position(2, 2)
        if (floorPlan.getTile(tickPosition).isSolid()) {
            floorPlan.setTile(FLOOR, tickPosition)
        } else {
            floorPlan.setTile(WALL, tickPosition)
        }
        // air flow
        //energy flow
    }
}