package org.rak.starsBetween.planets

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin(origins = ["*"])
@RestController
@RequestMapping("/planet")
class PlanetController {

    fun generatePlanet(seed: Long, density: Int, scale: Float, id: Int?) {

    }


}
