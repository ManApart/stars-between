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
import game.Game
import tile.Tile
import ui.Resources
import ui.VIRTUAL_SIZE
import ui.planetScene.PlanetScene
import wiring.loadGame

class ShipScene : Scene() {
    private lateinit var shipContainer: Container
    private val shipViewSize = 500

    override suspend fun Container.sceneInit() {
        Resources.init()
        loadGame()

        fixedSizeContainer(VIRTUAL_SIZE, VIRTUAL_SIZE, clip = false) {
            val controls = fixedSizeContainer(300, 600, clip = true) {
//                createControls(::regenerate, ::repaint)
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

    private suspend fun repaint() {
        shipContainer.removeChildren()
        shipContainer.paint(shipViewSize, Game.floorPlan, ::clickTile)
    }

    private fun clickTile(tile: Tile) {
        println("Clicked $tile")
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