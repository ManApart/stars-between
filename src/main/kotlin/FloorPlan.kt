class FloorPlan(val size: Int = 5) {
    val tiles = (0 until size).associate { x -> (x to (0 until size).associateWith { y -> Tile(Position(x, y)) }) }
    val allTiles = tiles.values.flatMap { it.values }

    fun getTile(pos: Position): Tile {
        return getTile(pos.x, pos.y)
    }

    fun getTile(x: Int, y: Int): Tile {
        return if (x in 0 until size && y in 0 until size) {
            tiles[x]!![y] ?: DEFAULT_TILE
        } else {
            DEFAULT_TILE
        }
    }
}