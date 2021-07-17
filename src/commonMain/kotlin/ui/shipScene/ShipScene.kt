package ui.shipScene

import com.soywiz.klock.TimeSpan
import com.soywiz.korge.scene.AlphaTransition
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.ui.uiButton
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.alignLeftToRightOf
import com.soywiz.korge.view.alignTopToBottomOf
import com.soywiz.korge.view.fixedSizeContainer
import com.soywiz.korio.async.launchImmediately
import floorplan.FloorPlan
import game.Game
import tile.Tile
import tile.defaultTiles
import tile.getDefault
import ui.Resources
import ui.VIRTUAL_SIZE
import ui.planetScene.PlanetScene
import wiring.loadGame

class ShipScene(private val floorPlan: FloorPlan = Game.floorPlan) : Scene() {
    private lateinit var shipContainer: Container
    private val shipViewSize = 500
    private val options = ShipViewOptions()

    override suspend fun Container.sceneInit() {
        Resources.init()
        loadGame()

        fixedSizeContainer(VIRTUAL_SIZE, VIRTUAL_SIZE, clip = false) {
            val controls = fixedSizeContainer(300, 600, clip = true) {
                createControls(::repaint, options)
            }
            shipContainer = fixedSizeContainer(shipViewSize, shipViewSize, clip = true) {
                alignLeftToRightOf(controls)
            }
            uiButton(text = "Planet") {
                alignTopToBottomOf(controls)
                onPress {
                    loadPlanetScene()
                }
            }
        }
        repaint(options)
    }

    private fun repaint(options: ShipViewOptions) {
        shipContainer.removeChildren()
        shipContainer.paint(shipViewSize, floorPlan, ::clickTile)
    }

    private fun clickTile(tile: Tile) {
        println("Clicked $tile")
        when( options.mode){
            ShipViewMode.BUILD -> clickBuild(tile)
            else -> clickBuild(tile)
        }
    }

    private fun clickBuild(tile: Tile) {
        val newTile = getDefault(options.selectedTileType)
        floorPlan.setTile(newTile, tile.position)
        repaint(options)
    }


    private fun loadPlanetScene() {
        launchImmediately {
            sceneContainer.changeTo<PlanetScene>(
                transition = AlphaTransition,
                time = TimeSpan(500.0)
            )
        }
    }
}