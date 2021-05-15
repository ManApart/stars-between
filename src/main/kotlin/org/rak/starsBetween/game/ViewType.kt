package org.rak.starsBetween.game

import org.rak.starsBetween.views.View
import org.rak.starsBetween.views.overview.OverView
import org.rak.starsBetween.views.powerView.PowerView
import org.rak.starsBetween.views.airView.Airview
import org.rak.starsBetween.views.shieldView.ShieldView
import org.rak.starsBetween.views.shieldView.toShieldView

enum class ViewType(val getView: () -> View) {
    OVERVIEW({ OverView(Game.floorPlan) }),
    AIR({ Airview(Game.floorPlan) }),
    CREW({ OverView(Game.floorPlan) }),
    POWER({ PowerView(Game.floorPlan) }),
    SHIELDS({ Game.floorPlan.toShieldView() });

}
