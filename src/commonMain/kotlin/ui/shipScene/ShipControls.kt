package ui.shipScene

import com.soywiz.korge.annotations.KorgeExperimental
import com.soywiz.korge.input.onClick
import com.soywiz.korge.ui.*
import com.soywiz.korge.view.*
import com.soywiz.korim.bitmap.NinePatchBmpSlice
import com.soywiz.korim.color.Colors
import tile.SystemType
import ui.Resources
import wiring.loadGame
import wiring.saveGame

@OptIn(KorgeExperimental::class)
fun Container.createControls(
    views: Views,
    repaint: () -> Unit,
    loadShipScene: () -> Unit,
    options: ShipViewOptions = ShipViewOptions()
) {
    removeChildren()
    val mainOptions = fixedSizeContainer(300, 60) {
        uiVerticalStack(width = 300.0) {

            uiHorizontalFill {
                uiButton(text = "Save") {
                    onPress {
                        views.saveGame()
                    }
                }
                uiButton(text = "Load") {
                    onPress {
                        views.loadGame()
                        loadShipScene()
                    }
                }
            }

            uiComboBox(items = ShipViewMode.values().toList()) {
                selectedItem = options.mode
                onSelectionUpdate {
                    if (it.selectedItem != null) {
                        options.mode = selectedItem!!
                        repaint()
                    }
                }
            }
        }
    }
    fixedSizeContainer(300, 600, clip = true) {
        alignTopToBottomOf(mainOptions)
        createModeControls(options)
    }

}

fun FixedSizeContainer.createModeControls(options: ShipViewOptions) {
    removeChildren()
    when (options.mode) {
        ShipViewMode.BUILD -> createBuildControls(options)
        ShipViewMode.AIR -> createBuildControls(options)
        else -> createBlankControls(options)
    }
}

fun FixedSizeContainer.createBuildControls(options: ShipViewOptions) {
    uiVerticalStack(width= 250.0) {
        uiHorizontalFill(height = 50.0) {
            val tileImage = Resources.getTileImage(options.selectedTileType, TILE_SIZE)
            fixedSizeContainer(50.0, 50.0, clip = true) {
                image(tileImage) {
                    scale = 5.0
                    smoothing = false
                }
            }
            text("Current: " + options.selectedTileType.name)

        }
        uiHorizontalFill(height= 20.0)
        SystemType.values().filter { it != SystemType.VOID }.forEach { tileType ->
            val tileImage = Resources.getTileImage(tileType, TILE_SIZE)
            uiHorizontalFill(height = 50.0) {
                fixedSizeContainer(50.0, 50.0, clip = true) {
                    image(tileImage) {
                        scale = 5.0
                        smoothing = false
                    }
                }
                text(tileType.name)
                onClick {
                    options.selectedTileType = tileType
                    this@createBuildControls.removeChildren()
                    this@createBuildControls.createBuildControls(options)
                }
            }
        }
    }
}

fun FixedSizeContainer.createBlankControls(options: ShipViewOptions) {

}