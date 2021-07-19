package ui.shipScene

import com.soywiz.kds.iterators.fastForEach
import com.soywiz.klock.TimeSpan
import com.soywiz.klock.timesPerSecond
import com.soywiz.korge.scene.AlphaTransition
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.ui.uiButton
import com.soywiz.korge.view.*
import com.soywiz.korio.async.launchImmediately
import floorplan.Ship
import game.Game
import tile.Tile
import tile.getDefault
import ui.Resources
import ui.VIRTUAL_SIZE
import ui.planetScene.PlanetScene
import wiring.loadGame

class ShipScene(var ship: Ship = Game.ship) : Scene() {
    private lateinit var shipContainer: Container
    private lateinit var controls: Container
    private lateinit var tiles: List<TileView>
    private lateinit var crew: List<CrewmanView>
    private val shipViewSize = 500
    private val options = ShipViewOptions()

    override suspend fun Container.sceneInit() {
        Resources.init()
        views.loadGame()
        ship = Game.ship
        options.ship = ship

        fixedSizeContainer(VIRTUAL_SIZE, VIRTUAL_SIZE, clip = false) {
            controls = fixedSizeContainer(300, VIRTUAL_SIZE - 40, clip = true) {
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

    private fun tick() {
        Game.tick()
        tiles.fastForEach { it.tick(options) }
        crew.fastForEach { it.tick() }
    }

    private fun paintShip() {
        controls.removeChildren()
        controls.createControls(views, ::paintShip, ::loadShipScene, options)

        shipContainer.removeChildren()
        tiles = shipContainer.paint(shipViewSize, ship.floorPlan, ::clickTile)
        crew = shipContainer.paintCrew(shipViewSize, ship.floorPlan.size, ship.crew.values.toList())
    }

    private fun clickTile(tile: Tile) {
        println("Clicked $tile")
        options.selectedTile = tile
        ship.floorPlan.setSelectedTile(tile)

        when (options.mode) {
            ShipViewMode.BUILD -> clickBuild(tile)
            ShipViewMode.AIR -> clickBuild(tile)
            ShipViewMode.CREW -> clickCrew(tile)
            else -> {
            }
        }
    }

    private fun clickBuild(tile: Tile) {
        val newTile = getDefault(options.buildTileType)
        ship.floorPlan.setTile(newTile, tile.position)
        paintShip()
    }

    private fun clickCrew(tile: Tile) {
        if (tile.crewMan != null) {
            println("Selected crewman ${tile.crewMan?.id}")
            options.selectedCrewMan = tile.crewMan
        } else {
            println("Ordered crewman ${options.selectedCrewMan?.id} to ${tile.position}")
            options.selectedCrewMan?.goal = tile
        }
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
                Game.ship,
                transition = AlphaTransition,
                time = TimeSpan(500.0)
            )
        }
    }
}