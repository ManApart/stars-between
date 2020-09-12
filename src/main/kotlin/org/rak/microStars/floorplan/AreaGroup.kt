package org.rak.microStars.floorplan

import org.rak.microStars.tile.Tile

class AreaGroup(val parent: FloorPlan, val isValidForDistance: (Tile) -> Boolean) {

    //creates list of Areas when floorplan changes
    fun calculateAreas(){

    }

    fun getAreas() : List<Area>{
        return listOf()
    }

}