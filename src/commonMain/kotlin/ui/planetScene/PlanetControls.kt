package ui.planetScene

import com.soywiz.korge.ui.uiComboBox
import com.soywiz.korge.view.Container
import ui.planetScene.planetView.PlanetViewType

fun Container.createControls(repaint: ()-> Unit) {
    uiComboBox(items = PlanetViewType.values().toList()).onSelectionUpdate {
        if (it.selectedItem != null) {
            println(it.selectedItem)
            PlanetManager.viewType = it.selectedItem!!
            repaint()
        }
    }

}