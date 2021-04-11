package org.rak.microStars.game

import org.rak.microStars.tile.SystemType
import org.rak.microStars.wiring.SocketManager
import org.rak.microStars.wiring.loadGame
import org.rak.microStars.wiring.saveGame
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["*"])
@RestController
@RequestMapping("/game")
class GameController {

    @GetMapping("/tileTypes")
    fun getTileTypes() : List<SystemType> {
        return SystemType.values().filter { it != SystemType.VOID }.toList()
    }

    @GetMapping("/views")
    fun getViews() : List<ViewType> {
        return ViewType.values().toList()
    }

    @PutMapping("/views")
    fun setView(@RequestParam viewType: ViewType) {
        Game.currentView = viewType
    }

    @PostMapping("/save")
    fun save() {
        saveGame()
    }

    @PostMapping("/load")
    fun load() {
        loadGame()
        SocketManager.sendUpdate()
    }

}
