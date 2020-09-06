package org.rak.microStars.floorplan

import org.rak.microStars.game.Game
import org.rak.microStars.wiring.SocketManager
import org.rak.microStars.tile.DEFAULT_TILE
import org.rak.microStars.tile.SimpleTile
import org.rak.microStars.tile.TileUpdate
import org.rak.microStars.tile.tileTypes
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["*"])
@RestController
@RequestMapping("/floorPlan")
class FloorPlanController {

    @GetMapping
    fun getFloorPlan(): SimpleFloorPlan {
        return SimpleFloorPlan(Game.floorPlan)
    }

    @PutMapping
    fun setTile(@RequestBody update: TileUpdate) : SimpleTile {
        val tile = tileTypes.firstOrNull { it.name.toLowerCase() == update.tileType.toLowerCase() } ?: DEFAULT_TILE

        Game.floorPlan.setTile(tile, update.x, update.y)
        SocketManager.sendUpdate()
        return SimpleTile(Game.floorPlan.getTile(update.x, update.y))
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