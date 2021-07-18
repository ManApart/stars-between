package ui.shipScene

import com.soywiz.korge.view.Container
import com.soywiz.korge.view.Image
import com.soywiz.korge.view.Text
import com.soywiz.korim.color.Colors
import power.Engine
import power.Powerable
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
            ShipViewMode.POWER -> updatePower()
            ShipViewMode.DISTANCE -> updateDistance()
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

    private fun updatePower() {
        if (tile.system.type == SystemType.ENGINE) {
            val engine = tile.system as Engine
            text.text = engine.power.toString()
        } else if (tile.system is Powerable) {
            val system = tile.system as Powerable
            text.text = system.power.toString()
        }
    }

    private fun updateDistance() {
        if (tile.distanceFromSelected != Int.MAX_VALUE) {
            text.text = tile.distanceFromSelected.toString()
        }
    }
}