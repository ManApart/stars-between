package game

import floorplan.Ship

object Game {
    var ship = Ship()

    fun tick() {
        ship.tick()
    }
}
