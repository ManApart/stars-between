package org.rak.starsBetween.game

import org.rak.starsBetween.views.View
import org.rak.starsBetween.views.overview.OverView
import org.rak.starsBetween.views.powerView.PowerView
import org.rak.starsBetween.views.airView.Airview
import org.rak.starsBetween.views.crewView.createCrewView
import org.rak.starsBetween.views.shieldView.toShieldView

enum class ViewType(val getView: () -> View) {
    OVERVIEW({ OverView(Game.floorPlan) }),
    AIR({ Airview(Game.floorPlan) }),
    CREW({ createCrewView(Game.floorPlan, Game.crew) }),
    POWER({ PowerView(Game.floorPlan) }),
    SHIELDS({ Game.floorPlan.toShieldView() });

}
