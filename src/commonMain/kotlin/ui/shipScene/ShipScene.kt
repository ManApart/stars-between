package ui.shipScene

import com.soywiz.kds.iterators.fastForEach
import com.soywiz.klock.TimeSpan
import com.soywiz.klock.timesPerSecond
import com.soywiz.korge.scene.AlphaTransition
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.ui.uiButton
import com.soywiz.korge.view.*
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
    private lateinit var tiles: List<TileView>
    private val shipViewSize = 500
    private val options = ShipViewOptions()

    override suspend fun Container.sceneInit() {
        Resources.init()
        views.loadGame()
        floorPlan = Game.floorPlan
        options.floorPlan = floorPlan

        fixedSizeContainer(VIRTUAL_SIZE, VIRTUAL_SIZE, clip = false) {
            controls = fixedSizeContainer(300, VIRTUAL_SIZE-40, clip = true) {
                createControls(views, ::paintShip, ::loadShipScene, options)
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
        paintShip()

        addFixedUpdater(10.timesPerSecond) {
            tick()
        }
    }

    private fun tick(){
        Game.tick()
        tiles.fastForEach { it.tick(options) }
    }

    private fun paintShip() {
        controls.removeChildren()
        controls.createControls(views, ::paintShip, ::loadShipScene, options)

        shipContainer.removeChildren()
        tiles = shipContainer.paint(shipViewSize, floorPlan, ::clickTile)
    }

    private fun clickTile(tile: Tile) {
        println("Clicked $tile")
        options.selectedTile = tile
        floorPlan.setSelectedTile(tile)

        when( options.mode){
            ShipViewMode.BUILD -> clickBuild(tile)
            ShipViewMode.AIR -> clickBuild(tile)
            else -> {}
        }
    }

    private fun clickBuild(tile: Tile) {
        val newTile = getDefault(options.buildTileType)
        floorPlan.setTile(newTile, tile.position)
        paintShip()
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