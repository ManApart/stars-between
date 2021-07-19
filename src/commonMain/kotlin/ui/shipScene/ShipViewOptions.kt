package ui.shipScene

import crew.CrewMan
import game.Game
import tile.SystemType

class ShipViewOptions{
    var mode: ShipViewMode = ShipViewMode.CREW
    var buildTileType = SystemType.SPACE
    var ship = Game.ship
    var selectedTile = ship.floorPlan.getTile(0,0)
    var selectedCrewMan: CrewMan? = null
}