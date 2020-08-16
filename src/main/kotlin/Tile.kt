val DEFAULT_TILE = Tile()

class Tile(
    val position: Position = Position(),
    var solid: Boolean = false
) {


    fun isEdgeTile(floorPlanSize: Int) : Boolean {
        return position.x == 0 || position.y == 0 || position.x == floorPlanSize - 1 || position.y == floorPlanSize - 1
    }
}