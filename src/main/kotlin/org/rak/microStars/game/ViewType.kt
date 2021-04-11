package org.rak.microStars.game

import org.rak.microStars.views.View
import org.rak.microStars.views.overview.SimpleFloorPlan

enum class ViewType(val getView: () -> View) {
    OVERVIEW({ SimpleFloorPlan(Game.floorPlan) }),
    AIR({ SimpleFloorPlan(Game.floorPlan) }),
    SHIELDS({ SimpleFloorPlan(Game.floorPlan) })
}
