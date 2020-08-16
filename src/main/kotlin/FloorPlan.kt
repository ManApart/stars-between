class FloorPlan(private val size: Int = 5) {
    val tiles = (0 until size).associateWith { (0 until size).associateWith { Tile() } }


    fun getTile(pos: Position): Tile {
        return tiles[pos.x]!![pos.y] ?: DEFAULT_TILE
    }
}