package ui.planetScene

import com.soywiz.korev.Key
import com.soywiz.korge.input.keys
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.*
import planet.generation.PlanetOptions
import planet.generation.PlanetOptionsUI
import ui.Resources
import ui.VIRTUAL_SIZE
import ui.planetScene.planetView.paint

class PlanetScene : Scene() {
    private lateinit var planetContainer: Container
    override suspend fun Container.sceneInit() {
        Resources.init()
        fixedSizeContainer(VIRTUAL_SIZE, VIRTUAL_SIZE, clip = false) {
            val controls = fixedSizeContainer(300, 600, clip = true) {
                createControls(::regenerate, ::repaint)
            }
            planetContainer = fixedSizeContainer(120, 120, clip = true) {
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

    private fun regenerate(options: PlanetOptionsUI) {
        PlanetManager.generatePlanet(0, options.toOptions())
        repaint()
    }

    private fun repaint() {
        val planet = PlanetManager.getPlanet(0)
        planetContainer.removeChildren()
        planetContainer.paint(planet, PlanetManager.viewType)
    }
}