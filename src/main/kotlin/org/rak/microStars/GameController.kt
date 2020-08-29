package org.rak.microStars

import org.rak.microStars.tile.tileTypes
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["*"])
@RestController
@RequestMapping("/game")
class GameController {

    @GetMapping("/tileTypes")
    fun getTileTypes() : List<String> {
        return tileTypes.map { it.name }
    }

    @PostMapping
    fun save() {
        println("Save not implemented")
    }

}