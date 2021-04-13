package org.rak.microStars.game

import org.rak.microStars.views.View
import org.rak.microStars.views.overview.SimpleFloorPlan
import org.rak.microStars.views.powerView.PowerView
import org.rak.microStars.views.airView.Airview
import org.rak.microStars.views.shieldView.ShieldView

enum class ViewType(val getView: () -> View) {
    OVERVIEW({ SimpleFloorPlan(Game.floorPlan) }),
    AIR({ Airview(Game.floorPlan) }),
    POWER({ PowerView(Game.floorPlan) }),
    SHIELDS({ ShieldView(Game.floorPlan) });

}
