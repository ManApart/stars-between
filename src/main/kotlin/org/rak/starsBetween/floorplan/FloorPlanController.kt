package org.rak.starsBetween.floorplan

import org.rak.starsBetween.game.Game
import org.rak.starsBetween.wiring.SocketManager
import org.rak.starsBetween.tile.DEFAULT_TILE
import org.rak.starsBetween.views.powerView.PowerViewTile
import org.rak.starsBetween.tile.TileUpdate
import org.rak.starsBetween.tile.defaultTiles
import org.rak.starsBetween.views.powerView.PowerView
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["*"])
@RestController
@RequestMapping("/floorPlan")
class FloorPlanController {

    @GetMapping
    fun getFloorPlan(): PowerView {
        return PowerView(Game.floorPlan)
    }

    @PutMapping
    fun setTile(@RequestBody update: TileUpdate) : PowerViewTile {
        val tile = defaultTiles.firstOrNull { it.system.type == update.systemType } ?: DEFAULT_TILE

        Game.floorPlan.setTile(tile, update.x, update.y)
        SocketManager.sendUpdate()
        return PowerViewTile(Game.floorPlan.getTile(update.x, update.y))
    }

    @PostMapping
    fun createFloorPlan(@RequestParam size: Int = 10) {
        Game.floorPlan = Game.createFloorPlan(size)
    }

    @PostMapping("/tile")
    fun selectTile(@RequestParam x: Int, @RequestParam y: Int) {
        Game.floorPlan.setSelectedTile(Game.floorPlan.getTile(x, y))
    }

}
