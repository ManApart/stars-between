val SPACE = Tile("Space", airProduced = -10)
val FLOOR = Tile("Floor")
val WALL = Tile("Wall", solid = true)
val VENT = Tile("Vent", airProduced = 10)
val DEFAULT_TILE = Tile("Void")

data class Tile(
    val name: String,
    val position: Position = Position(),
    private val totalHealth: Int = 100,
    private val solid: Boolean = false,
    val airProduced: Int = -1
) {

    var health = totalHealth
    var air = 0
    var adjacency = Adjacency.NONE
    var rotation = 0

    fun isSolid(): Boolean {
        return solid && health > 0
    }

    fun isType(other: Tile): Boolean {
        return name == other.name
    }

    fun isEdgeTile(floorPlanSize: Int): Boolean {
        return position.x == 0 || position.y == 0 || position.x == floorPlanSize - 1 || position.y == floorPlanSize - 1
    }
}