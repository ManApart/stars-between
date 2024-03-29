package ui.planetScene

import com.soywiz.korge.annotations.KorgeExperimental
import com.soywiz.korge.input.onClick
import com.soywiz.korge.input.onOut
import com.soywiz.korge.input.onOver
import com.soywiz.korge.input.onUp
import com.soywiz.korge.ui.*
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.collidesWith
import com.soywiz.korge.view.findCollision
import epochMillis
import planet.BiomeType
import planet.generation.PlanetOptions
import planet.generation.PlanetOptionsUI
import randRange
import ui.planetScene.planetView.PlanetViewType

@OptIn(KorgeExperimental::class)
fun Container.createControls(
    regenerate: (PlanetOptionsUI) -> Unit,
    repaint: (PlanetOptionsUI) -> Unit,
    optionsUI: PlanetOptionsUI? = null
) {
    val options = optionsUI ?: PlanetOptionsUI(regenerate)
    removeChildren()
    uiVerticalStack(width = 300.0) {

        // hack to prevent activating random seed when pressing the dropdown
        var comboActive = false
        uiComboBox(items = PlanetViewType.values().toList()) {
            selectedItem = PlanetViewType.BIOME
            onSelectionUpdate {
                if (it.selectedItem != null) {
                    options.viewType = it.selectedItem!!
                    repaint(options)
                }
            }
            onOver { comboActive = true }
            onOut { comboActive = false }
        }

        uiCheckBox(text = "Auto Update", checked = options.autoUpdate) {
            onChange {
                options.autoUpdate = it.checked
            }
        }

        uiCheckBox(text = "Sphere", checked = options.sphere) {
            onChange {
                options.sphere = it.checked
            }
        }

        uiCheckBox(text = "Shadow", checked = options.shadow) {
            onChange {
                options.shadow = it.checked
            }
        }

        uiPropertyNumberRow("Seed", *UIEditableNumberPropsList(options::seed, min = 0.0, max = 9000.0))
        uiButton(text = "Random Seed") {
            onPress {
                if (!comboActive) {
                    options.seed = randRange(epochMillis(), 0, 9000).toDouble()
                    regenerate(options)
                    this@createControls.createControls(regenerate, repaint, options)
                }
            }
        }

        uiComboBox(items = BiomeType.values().toList()) {
            selectedItem = BiomeType.EARTH_LIKE
            onSelectionUpdate {
                if (it.selectedItem != null) {
                    options.biomeType = selectedItem!!
                    repaint(options)
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