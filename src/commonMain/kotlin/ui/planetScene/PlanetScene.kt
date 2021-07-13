package ui.planetScene

import com.soywiz.korev.Key
import com.soywiz.korge.component.docking.dockedTo
import com.soywiz.korge.input.keys
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.*
import com.soywiz.korim.color.Colors
import com.soywiz.korma.geom.vector.line
import planet.Planet
import ui.Resources
import ui.WINDOW_SIZE
import ui.planetScene.planetView.paint

class PlanetScene : Scene() {
    private lateinit var planetContainer: Container
    override suspend fun Container.sceneInit() {
        Resources.init()
        fixedSizeContainer(WINDOW_SIZE, WINDOW_SIZE, clip = false) {
            val controls = fixedSizeContainer(200, 600, clip = true) {
                solidRect(200, 600)
                createControls(::repaint)
            }
            planetContainer = fixedSizeContainer(100, 100, clip = true) {
                alignLeftToRightOf(controls)
                scale = 5.0
            }
        }

        keys {
            up(Key.SPACE) {
                println("Space pressed")
            }
        }

        repaint()
    }

    private fun repaint() {
        val planet = PlanetManager.getPlanet(0)
        planetContainer.removeChildren()
        planetContainer.paint(planet, PlanetManager.viewType)
    }
}