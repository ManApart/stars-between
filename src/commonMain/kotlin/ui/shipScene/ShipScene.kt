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
import ui.VIRTUAL_SIZE
import ui.planetScene.PlanetScene
import wiring.loadGame

class ShipScene() : Scene() {

    override suspend fun Container.sceneInit() {
        loadGame()

        fixedSizeContainer(VIRTUAL_SIZE, VIRTUAL_SIZE, clip = false) {
            val controls = fixedSizeContainer(300, 600, clip = true) {
//                createControls(::regenerate, ::repaint)
            }
            fixedSizeContainer(120, 120, clip = true) {
                alignLeftToRightOf(controls)
                scale = 5.0
            }
            uiButton(text= "Planet") {
                alignTopToBottomOf(controls)
                onPress {
                    loadPlanetScene()
                }
            }
        }

    }


    private fun loadPlanetScene(){
        launchImmediately {
            sceneContainer.changeTo<PlanetScene>(
                transition = AlphaTransition,
                time = TimeSpan(500.0)
            )
        }
    }
}