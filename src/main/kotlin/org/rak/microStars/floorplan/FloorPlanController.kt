package org.rak.microStars.floorplan

import org.rak.microStars.game.Game
import org.rak.microStars.wiring.SocketManager
import org.rak.microStars.tile.DEFAULT_TILE
import org.rak.microStars.views.powerView.PowerViewTile
import org.rak.microStars.tile.TileUpdate
import org.rak.microStars.tile.defaultTiles
import org.rak.microStars.views.powerView.PowerView
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
