package ui.shipScene

import com.soywiz.korge.input.onClick
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.fixedSizeContainer
import com.soywiz.korge.view.position
import com.soywiz.korge.view.solidRect
import com.soywiz.korim.color.Colors
import floorplan.FloorPlan
import tile.Tile

private const val TILE_SIZE = 10

fun Container.paint(
    shipViewSize: Int,
    floorPlan: FloorPlan,
    click: (Tile) -> Unit,
    options: ShipViewOptions = ShipViewOptions()
) {
    val displaySize = floorPlan.size
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
    solidRect(TILE_SIZE, TILE_SIZE, color = Colors.PINK) {
        position(x * TILE_SIZE, y * TILE_SIZE)
        onClick {
            click(tile)
        }
    }

}