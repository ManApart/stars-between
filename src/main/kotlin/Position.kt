class Position(val x: Int = 0, val y: Int = 0) {
    override fun toString(): String {
        return "($x, $y)"
    }

    fun up() : Position {
        return Position(x, y-1)
    }

    fun down() : Position {
        return Position(x, y+1)
    }

    fun left() : Position {
        return Position(x-1, y)
    }

    fun right() : Position {
        return Position(x+1, y)
    }

    fun neighbors() : List<Position> {
        return listOf(up(), down(), left(), right())
    }

}