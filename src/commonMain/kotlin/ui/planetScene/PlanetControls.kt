package ui.planetScene

import com.soywiz.korge.annotations.KorgeExperimental
import com.soywiz.korge.ui.*
import com.soywiz.korge.view.Container
import epochMillis
import planet.BiomeType
import planet.generation.PlanetOptions
import planet.generation.PlanetOptionsUI
import randRange
import ui.planetScene.planetView.PlanetViewType

@OptIn(KorgeExperimental::class)
fun Container.createControls(
    regenerate: (PlanetOptionsUI) -> Unit,
    repaint: () -> Unit,
    optionsUI: PlanetOptionsUI? = null
) {
    val options = optionsUI ?: PlanetOptionsUI(regenerate)
    removeChildren()
    uiVerticalStack(width = 300.0) {

        uiComboBox(items = PlanetViewType.values().toList()) {
            selectedItem = PlanetViewType.BIOME
            onSelectionUpdate {
                if (it.selectedItem != null) {
                    PlanetManager.viewType = it.selectedItem!!
                    repaint()
                }
            }
        }

        uiCheckBox(text = "Auto Update", checked = options.autoUpdate) {
            onChange {
                options.autoUpdate = it.checked
            }
        }

        uiPropertyNumberRow("Seed", *UIEditableNumberPropsList(options::seed, min = 0.0, max = 9000.0))
        uiButton(text = "Random Seed") {
            onPress {
                options.seed = randRange(epochMillis(), 0, 9000).toDouble()
                regenerate(options)
                this@createControls.createControls(regenerate, repaint, options)
            }
        }

        uiComboBox(items = BiomeType.values().toList()) {
            selectedItem = BiomeType.EARTH_LIKE
            onSelectionUpdate {
                if (it.selectedItem != null) {
                    options.biomeType = selectedItem!!
                    repaint()
                }
            }
        }

        uiPropertyNumberRow("Density", *UIEditableNumberPropsList(options::density, min = 10.0, max = 1000.0))

        uiPropertyNumberRow("Octaves", *UIEditableNumberPropsList(options::octaves, min = 1.0, max = 10.0))
        uiPropertyNumberRow("Roughness", *UIEditableNumberPropsList(options::roughness, min = 0.0, max = 2.0))
        uiPropertyNumberRow("noiseScale", *UIEditableNumberPropsList(options::noiseScale, min = 1.0, max = 10.0))

        uiPropertyNumberRow("Temperature", *UIEditableNumberPropsList(options::temperature, min = -200.0, max = 1000.0))
        uiPropertyNumberRow(
            "Temperature Variance",
            *UIEditableNumberPropsList(options::temperatureVariance, min = 0.0, max = 500.0)
        )
        uiPropertyNumberRow(
            "Temperature Factor",
            *UIEditableNumberPropsList(options::temperatureFactor, min = 1.0, max = 5.0)
        )

        uiPropertyNumberRow(
            "Default Precipitation",
            *UIEditableNumberPropsList(options::defaultPrecipitation, min = 0.0, max = 500.0)
        )
        uiPropertyNumberRow(
            "Water Threshold",
            *UIEditableNumberPropsList(options::waterThreshold, min = -200.0, max = 1000.0)
        )

        uiButton(text = "Reset") {
            onPress {
                val newOptions = PlanetOptionsUI(regenerate)
                regenerate(newOptions)
                this@createControls.createControls(regenerate, repaint, newOptions)
            }
        }
        uiButton(text = "Generate") {
            onPress {
                regenerate(options)
            }
        }
    }

}