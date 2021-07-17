package ui.planetScene

import com.soywiz.klock.TimeSpan
import com.soywiz.korev.Key
import com.soywiz.korge.input.keys
import com.soywiz.korge.scene.AlphaTransition
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.ui.uiButton
import com.soywiz.korge.ui.uiText
import com.soywiz.korge.ui.uiVerticalStack
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
    private lateinit var regionContainer: Container
    private val scaledPlanetSize = 525

    override suspend fun Container.sceneInit() {
        Resources.init()

        fixedSizeContainer(VIRTUAL_SIZE, VIRTUAL_SIZE, clip = false) {
            val controls = fixedSizeContainer(300, 600, clip = true) {
                createControls(::regenerate, ::repaint)
            }

            planetContainer = fixedSizeContainer(scaledPlanetSize, scaledPlanetSize, clip = true) {
                alignLeftToRightOf(controls)

            }
            regionContainer = fixedSizeContainer(200, 400, clip = false) {
                alignLeftToRightOf(planetContainer)
            }
            uiButton(text = "Ship") {
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
        repaintRegionInfo(PlanetManager.getPlanet(0).regions[0][0])
    }

    private fun clickPlanet(clickedPoint: Point) {
        val planet = PlanetManager.getPlanet(0)
        val x = kotlin.math.min(clickedPoint.x, planet.regions.size - 1.toDouble()).roundToInt()
        val y = kotlin.math.min(clickedPoint.y, planet.regions.size - 1.toDouble()).roundToInt()
        val region = planet.regions[x][y]
        repaintRegionInfo(region, x, y)
    }

    private fun regenerate(options: PlanetOptionsUI) {
        PlanetManager.generatePlanet(0, options.toOptions())
        repaint(options)
    }

    private fun repaint(options: PlanetOptionsUI) {
        val planet = PlanetManager.getPlanet(0)
        planetContainer.removeChildren()
        planetContainer.paint(scaledPlanetSize, ::clickPlanet, planet, options.toViewOptions())
    }

    private fun repaintRegionInfo(region: Region, x: Int = 0, y: Int = 0) {
        regionContainer.removeChildren()
        regionContainer.apply {
            uiVerticalStack(width = 200.0) {
                uiText("Position: $x $y")
                uiText("Altitiude: ${region.altitude}")
                uiText("Temperature: ${region.temperature}")
                uiText("Precipitation: ${region.precipitation}")
                uiText("Biome Name: ${region.biome.name}")
                uiText("Biome Altitude: ${region.biome.altitude}")
                uiText("Biome Temperature: ${region.biome.temperature}")
                uiText("Biome Precipitation: ${region.biome.precipitation}")
            }
        }
    }

    private fun loadShipScene() {
        launchImmediately {
            sceneContainer.changeTo<ShipScene>(
                transition = AlphaTransition,
                time = TimeSpan(500.0)
            )
        }
    }
}