class Game {
    val floorPlan = FloorPlan(5)

    init {
        floorPlan.allTiles.filter { it.isEdgeTile(floorPlan.size) }.forEach { it.solid = true }
    }

    fun tick() {
        val switchTile = floorPlan.getTile(2,2)
        floorPlan.getTile(2,2).solid = !floorPlan.getTile(2,2).solid
        // air flow
        //energy flow
    }
}