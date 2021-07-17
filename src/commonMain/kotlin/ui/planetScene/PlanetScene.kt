package ui.planetScene

import com.soywiz.klock.TimeSpan
import com.soywiz.korev.Key
import com.soywiz.korge.input.keys
import com.soywiz.korge.input.onClick
import com.soywiz.korge.scene.AlphaTransition
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.ui.uiButton
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.alignLeftToRightOf
import com.soywiz.korge.view.alignTopToBottomOf
import com.soywiz.korge.view.fixedSizeContainer
import com.soywiz.korio.async.launchImmediately
import com.soywiz.korma.geom.Point
import planet.Region
import planet.generation.PlanetOptionsUI
import ui.Resources
import ui.VIRTUAL_SIZE
import ui.planetScene.planetView.paint
import ui.shipScene.ShipScene
import kotlin.math.roundToInt

class PlanetScene : Scene() {
    private lateinit var planetContainer: Container
    private lateinit var selectedRegion: Region

    override suspend fun Container.sceneInit() {
        Resources.init()
        selectedRegion = PlanetManager.getPlanet(0).regions[0][0]

        fixedSizeContainer(VIRTUAL_SIZE, VIRTUAL_SIZE, clip = false) {
            val controls = fixedSizeContainer(300, 600, clip = true) {
                createControls(::regenerate, ::repaint)
            }
            planetContainer = fixedSizeContainer(120, 120, clip = true) {
                alignLeftToRightOf(controls)
                scale = 5.0

                onClick {
                    clickPlanet(it.currentPosLocal)
                }
            }
            uiButton(text= "Ship") {
                alignTopToBottomOf(controls)
                onPress {
                    loadShipScene()
                }
            }
        }

        keys {
            up(Key.SPACE) {
                println("Space pressed")
            }
        }


        repaint(PlanetOptionsUI())
    }

    private fun clickPlanet(clickedPoint: Point) {
        val planet = PlanetManager.getPlanet(0)
        val x = kotlin.math.min(clickedPoint.x, planet.regions.size-1.toDouble()).roundToInt()
        val y = kotlin.math.min(clickedPoint.x, planet.regions.size-1.toDouble()).roundToInt()
        val region = planet.regions[x][y]
        println(region)
    }

    private fun regenerate(options: PlanetOptionsUI) {
        PlanetManager.generatePlanet(0, options.toOptions())
        repaint(options)
    }

    private fun repaint(options: PlanetOptionsUI) {
        val planet = PlanetManager.getPlanet(0)
        planetContainer.removeChildren()
        planetContainer.paint(planet, options.toViewOptions())
    }

    private fun loadShipScene(){
        launchImmediately {
            sceneContainer.changeTo<ShipScene>(
                transition = AlphaTransition,
                time = TimeSpan(500.0)
            )
        }
    }
}