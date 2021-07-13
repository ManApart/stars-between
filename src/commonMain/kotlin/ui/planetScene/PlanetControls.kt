package ui.planetScene

import com.soywiz.korge.view.Container
import com.soywiz.korge.view.roundRect
import com.soywiz.korim.color.Colors
import planet.Planet
import ui.button
import ui.planetScene.planetView.PlanetViewType

fun Container.createControls() {
//    button(PlanetViewType.ALTITUDE.name)
    var offset = 10.0
    PlanetViewType.values().forEach { view ->
        roundRect(150.0, 40.0, 1.0, fill = Colors.BEIGE) {
            y = offset
        }
        offset += 45
    }
}