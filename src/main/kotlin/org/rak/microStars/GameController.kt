package org.rak.microStars

import org.rak.microStars.tile.tileTypes
import org.rak.microStars.wiring.SocketManager
import org.rak.microStars.wiring.loadGame
import org.rak.microStars.wiring.saveGame
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["*"])
@RestController
@RequestMapping("/game")
class GameController {

    @GetMapping("/tileTypes")
    fun getTileTypes() : List<String> {
        return tileTypes.map { it.name }
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