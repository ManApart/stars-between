class Game {
    val floorPlan = FloorPlan(5)

    init {
        val spaceTiles = floorPlan.getAllTiles().filter { it.isEdgeTile(floorPlan.size) }.map { it.position }
        val wallTiles = floorPlan.getAllTiles().filter { hasSpaceNeighbor(it) }.map { it.position }.filter { !spaceTiles.contains(it) }
        val floorTiles =
            floorPlan.getAllTiles().map { it.position }.filter { !spaceTiles.contains(it) && !wallTiles.contains(it) }

        spaceTiles.forEach { floorPlan.setTile(SPACE, it) }
        wallTiles.forEach { floorPlan.setTile(WALL, it) }
        floorTiles.forEach { floorPlan.setTile(FLOOR, it) }
    }

    private fun hasSpaceNeighbor(tile: Tile) = floorPlan.getNeighbors(tile).any { neighbor -> neighbor.isType(SPACE) }

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