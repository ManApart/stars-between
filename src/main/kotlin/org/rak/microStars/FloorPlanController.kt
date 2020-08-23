package org.rak.microStars

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
    fun setTile(@RequestBody update: TileUpdate) : SimpleTile{
        val tile = tileTypes.firstOrNull { it.name.toLowerCase() == update.tileType.toLowerCase() } ?: DEFAULT_TILE

        Game.floorPlan.setTile(tile, update.x, update.y)
        SocketManager.socket.send(SimpleFloorPlan(Game.floorPlan))
        return SimpleTile(Game.floorPlan.getTile(update.x, update.y))
    }

    @PostMapping
    fun createFloorPlan(@RequestParam size: Int = 10) {
        Game.floorPlan = Game.createFloorPlan(size)
    }

}