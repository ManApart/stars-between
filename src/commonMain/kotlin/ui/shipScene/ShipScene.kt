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
import tile.getDefault
import ui.Resources
import ui.VIRTUAL_SIZE
import ui.planetScene.PlanetScene
import wiring.loadGame

class ShipScene(var floorPlan: FloorPlan = Game.floorPlan) : Scene() {
    private lateinit var shipContainer: Container
    private lateinit var controls: Container
    private val shipViewSize = 500
    private val options = ShipViewOptions()

    override suspend fun Container.sceneInit() {
        Resources.init()
        views.loadGame()
        floorPlan = Game.floorPlan

        fixedSizeContainer(VIRTUAL_SIZE, VIRTUAL_SIZE, clip = false) {
            controls = fixedSizeContainer(300, 600, clip = true) {
                createControls(views, ::repaint, ::loadShipScene, options)
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
        repaint()
    }

    private fun repaint() {
        controls.removeChildren()
        controls.createControls(views, ::repaint, ::loadShipScene, options)

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
        repaint()
    }

    private fun loadPlanetScene() {
        launchImmediately {
            sceneContainer.changeTo<PlanetScene>(
                transition = AlphaTransition,
                time = TimeSpan(500.0)
            )
        }
    }

    private fun loadShipScene() {
        launchImmediately {
            sceneContainer.changeTo<ShipScene>(
                Game.floorPlan,
                transition = AlphaTransition,
                time = TimeSpan(500.0)
            )
        }
    }
}