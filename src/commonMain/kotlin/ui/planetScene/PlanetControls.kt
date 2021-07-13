package ui.planetScene

import com.soywiz.korge.input.onClick
import com.soywiz.korge.ui.uiButton
import com.soywiz.korge.ui.uiComboBox
import com.soywiz.korge.ui.uiVerticalStack
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.position
import com.soywiz.korge.view.roundRect
import com.soywiz.korim.color.Colors
import planet.Planet
import ui.button
import ui.planetScene.planetView.PlanetViewType

fun Container.createControls() {
//    button(PlanetViewType.ALTITUDE.name)
    var offset = 10.0

    uiComboBox(items = PlanetViewType.values().toList()).onSelectionUpdate {
        println(it.selectedItem)
    }

//    uiVerticalStack(width = 150.0) {
//        PlanetViewType.values().forEach { view ->
//            uiButton(text = view.name) {
//                onClick {
//                    println("Clicked ${view.name}")
//                }
//            }
//        }
//    }.position(0.0, 32.0)


//    PlanetViewType.values().forEach { view ->
//        roundRect(150.0, 40.0, 1.0, fill = Colors.BEIGE) {
//            y = offset
//        }
//        offset += 45
//    }
}