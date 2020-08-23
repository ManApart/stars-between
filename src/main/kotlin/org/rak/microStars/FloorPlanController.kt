package org.rak.microStars

import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RestController

@RestController("/floorplan")
class FloorPlanController {

    @PutMapping
    fun setTile(tile: String, x: Int, y: Int){

    }

}