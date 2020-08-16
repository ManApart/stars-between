class Game {
    val floorPlan = FloorPlan(5)

    init {
        floorPlan.allTiles.filter { it.isEdgeTile(floorPlan.size) }.forEach { it.solid = true }
    }

    fun tick() {
        // air flow
        //energy flow
    }
}