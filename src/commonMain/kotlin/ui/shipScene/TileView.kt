package ui.shipScene

import com.soywiz.korge.view.Container
import com.soywiz.korge.view.Image
import com.soywiz.korge.view.Text
import com.soywiz.korim.color.Colors
import com.soywiz.korim.color.RGBA
import tile.SPACE
import tile.SystemType
import tile.Tile

data class TileView(
    private val tile: Tile,
    private val square: Container,
    private val image: Image,
    private val text: Text
) {
    fun tick(options: ShipViewOptions) {
        when (options.mode) {
            ShipViewMode.AIR -> updateAir()
            else -> {
            }
        }
    }

    private fun updateAir() {
        if (!tile.system.isSolid() && tile.system.type != SystemType.SPACE) {
            if (tile.air != 100) {
                text.text = tile.air.toString()
            }

            if (tile.air < 50) {
                image.alpha = (50 + tile.air) / 100.0
            } else {
                image.tint = Colors.WHITE
            }
        }
    }
}