package org.rak.starsBetween.floorplan

class Position(val x: Int = 0, val y: Int = 0) {
    override fun toString(): String {
        return "($x, $y)"
    }

    override fun equals(other: Any?): Boolean {
        return if (other is Position) {
            x == other.x && y == other.y
        } else {
            super.equals(other)
        }
    }

    fun up(): Position {
        return Position(x, y - 1)
    }

    fun down(): Position {
        return Position(x, y + 1)
    }

    fun left(): Position {
        return Position(x - 1, y)
    }

    fun right(): Position {
        return Position(x + 1, y)
    }

    fun neighbors(): List<Position> {
        return listOf(up(), down(), left(), right())
    }

    fun offset(x: Int = 0, y: Int = 0): Position {
        return Position(this.x + x, this.y + y)
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        return result
    }

}
