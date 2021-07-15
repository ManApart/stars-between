package ui.planetScene

import com.soywiz.korge.ui.*
import com.soywiz.korge.view.Container
import epochMillis
import planet.generation.PlanetOptions
import planet.generation.PlanetOptionsUI
import randRange
import ui.planetScene.planetView.PlanetViewType

fun Container.createControls(regenerate: (PlanetOptions) -> Unit, repaint: () -> Unit) {
    val options = PlanetOptionsUI()
    uiVerticalStack {
        uiComboBox(items = PlanetViewType.values().toList()) {
            selectedItem = PlanetViewType.BIOME
            onSelectionUpdate {
                if (it.selectedItem != null) {
                    PlanetManager.viewType = it.selectedItem!!
                    repaint()
                }
            }
        }

        val prop = UIEditableNumberPropsList(options::seed, min = 0.0, max = 9000.0)
        uiPropertyNumberRow("Seed", *prop)
        uiPropertyNumberRow("Density", *UIEditableNumberPropsList(options::density, min = 100.0, max = 1000.0))
        uiButton(text = "Random Seed") {
            onPress {
                options.seed = randRange(epochMillis(), 0, 9000).toDouble()
                prop.first().onChange(options.seed)
                regenerate(options.toOptions())
            }
        }
        uiButton(text = "Generate") {
            onPress {
                regenerate(options.toOptions())
            }
        }
    }

}