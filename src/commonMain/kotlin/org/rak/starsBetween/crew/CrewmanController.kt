package org.rak.starsBetween.crew

import org.rak.starsBetween.game.Game
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["*"])
@RestController
@RequestMapping("/crew")
class CrewmanController {

    @PostMapping
    fun addCrewman() {
        Game.addCrewMan()
    }

    @DeleteMapping
    fun removeCrewman(@RequestParam crewmanId: Int) {
        println("Remove Crewman")
    }


    @PutMapping
    fun givOrder(@RequestParam crewmanId: Int, @RequestParam x: Int, @RequestParam y: Int) {

    }


}
