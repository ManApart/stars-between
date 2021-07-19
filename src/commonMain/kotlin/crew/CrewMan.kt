package crew

import tile.Tile

class CrewMan(val id: Int, val division: Division, var tile: Tile) {
    var goal: Tile? = null
}