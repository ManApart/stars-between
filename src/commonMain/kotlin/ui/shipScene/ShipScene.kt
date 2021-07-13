package ui.shipScene

import com.soywiz.korev.Key
import com.soywiz.korge.input.keys
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.fixedSizeContainer
import com.soywiz.korge.view.solidRect
import com.soywiz.korim.color.Colors
import ui.VIRTUAL_SIZE
import wiring.loadGame
import wiring.saveGame

class ShipScene() : Scene() {

    override suspend fun Container.sceneInit() {
        saveGame()
        loadGame()

        fixedSizeContainer(VIRTUAL_SIZE, VIRTUAL_SIZE, clip = false) {
            solidRect(40, 40, Colors.BLUE)
        }

        keys {
            up(Key.SPACE) {
                println("Space pressed")
            }
        }

    }
}