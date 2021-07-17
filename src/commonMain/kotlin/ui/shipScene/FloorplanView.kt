package ui.shipScene

import com.soywiz.korge.input.onClick
import com.soywiz.korge.view.*
import com.soywiz.korim.bitmap.Bitmap
import com.soywiz.korim.bitmap.BitmapSlice
import com.soywiz.korim.color.Colors
import com.soywiz.korma.geom.degrees
import floorplan.FloorPlan
import tile.Adjacency
import tile.Tile
import ui.Resources

const val TILE_SIZE = 10

fun Container.paint(
    shipViewSize: Int,
    floorPlan: FloorPlan,
    click: (Tile) -> Unit,
    options: ShipViewOptions = ShipViewOptions()
) {
    val displaySize = floorPlan.size * TILE_SIZE
    val scaleSize = shipViewSize.toDouble() / displaySize
    fixedSizeContainer(displaySize, displaySize, clip = true) {
        scale = scaleSize
        (0 until floorPlan.size).forEach { x ->
            (0 until floorPlan.size).forEach { y ->
                paint(floorPlan, click, options, x, y)
            }
        }
    }
}

private fun Container.paint(
    floorPlan: FloorPlan,
    click: (Tile) -> Unit,
    options: ShipViewOptions = ShipViewOptions(),
    x: Int,
    y: Int
) {
    val tile = floorPlan.getTile(x, y)
    val image = tile.getTileImage()
    val displayX = x * TILE_SIZE.toDouble()
    val displayY = y * TILE_SIZE.toDouble()
    image(image, anchorX = displayX + TILE_SIZE.toDouble()/2, anchorY = displayY + TILE_SIZE.toDouble()/2) {
        position(displayX, displayY)
        smoothing = false
        centered
        rotation = tile.rotation.degrees
        onClick {
            click(tile)
        }
    }

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