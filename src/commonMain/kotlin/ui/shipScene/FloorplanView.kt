package ui.shipScene

import com.soywiz.korge.input.onClick
import com.soywiz.korge.view.*
import com.soywiz.korim.bitmap.Bitmap
import com.soywiz.korim.bitmap.BitmapSlice
import com.soywiz.korim.color.Colors
import com.soywiz.korma.geom.degrees
import crew.CrewMan
import floorplan.FloorPlan
import tile.Adjacency
import tile.Tile
import ui.Resources
import kotlin.properties.Delegates

const val TILE_SIZE = 10

fun Container.paint(
    shipViewSize: Int,
    floorPlan: FloorPlan,
    click: (Tile) -> Unit
): List<TileView> {
    val displaySize = floorPlan.size * TILE_SIZE
    val scaleSize = shipViewSize.toDouble() / displaySize
    var tiles: List<TileView> by Delegates.notNull()
    fixedSizeContainer(displaySize, displaySize, clip = true) {
        scale = scaleSize
        tiles = (0 until floorPlan.size).flatMap { x ->
            (0 until floorPlan.size).map { y ->
                paint(floorPlan, click, x, y)
            }
        }
    }
    return tiles
}

private fun Container.paint(
    floorPlan: FloorPlan,
    click: (Tile) -> Unit,
    x: Int,
    y: Int
): TileView {
    val tile = floorPlan.getTile(x, y)
    val image = tile.getTileImage()
    val displayX = x * TILE_SIZE.toDouble()
    val displayY = y * TILE_SIZE.toDouble()
    var squareImage: Image by Delegates.notNull()
    var text: Text by Delegates.notNull()

    val square = fixedSizeContainer(TILE_SIZE, TILE_SIZE) {
        position(displayX, displayY)
        squareImage = image(image) {
            position(TILE_SIZE / 2, TILE_SIZE / 2)
            smoothing = false
            centered
            rotation = tile.rotation.degrees
            onClick {
                click(tile)
            }
        }
        text = text("", textSize = 4.0, color = Colors.SLATEGRAY)
    }
    return TileView(tile, floorPlan, square, squareImage, text)
}

fun Tile.getTileImage(): BitmapSlice<Bitmap> {
    val col = when (adjacency) {
        Adjacency.NONE -> 2
        Adjacency.ONE_SIDE -> 1
        Adjacency.TWO_SIDE -> 0
        Adjacency.CORNER -> 2
        Adjacency.THREE_SIDE -> 1
        Adjacency.ALL -> 0
    }
    val row = when (adjacency) {
        Adjacency.NONE -> 1
        Adjacency.ONE_SIDE -> 1
        Adjacency.TWO_SIDE -> 1
        Adjacency.CORNER -> 0
        Adjacency.THREE_SIDE -> 0
        Adjacency.ALL -> 0
    }

    return Resources.getTileImage(system.type, TILE_SIZE, col, row)
}

fun Container.paintCrew(shipViewSize: Int, floorPlanSize: Int, crew: List<CrewMan>): List<CrewmanView> {
    val displaySize = floorPlanSize * TILE_SIZE
    val scaleSize = shipViewSize.toDouble() / displaySize
    var crewViews: List<CrewmanView> by Delegates.notNull()
    fixedSizeContainer(displaySize, displaySize, clip = true) {
        scale = scaleSize
        crewViews = crew.map { man ->
            val circle = circle(TILE_SIZE.toDouble()/2, fill = man.division.color) {
                position(man.tile.position.x * TILE_SIZE, man.tile.position.y * TILE_SIZE)
            }
            CrewmanView(man, circle)
        }
    }
    return crewViews
}