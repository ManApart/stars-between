package ui.shipScene

import com.soywiz.korge.view.Container
import com.soywiz.korge.view.Image
import com.soywiz.korge.view.Text
import com.soywiz.korim.color.Colors
import floorplan.FloorPlan
import power.Engine
import power.Powerable
import systems.shields.Shield
import tile.SystemType
import tile.Tile

data class TileView(
    private val tile: Tile,
    private val floorPlan: FloorPlan,
    private val square: Container,
    private val image: Image,
    private val text: Text
) {
    fun tick(options: ShipViewOptions) {
        when (options.mode) {
            ShipViewMode.AIR -> updateAir()
            ShipViewMode.CREW -> updateCrew(options)
            ShipViewMode.DISTANCE -> updateDistance()
            ShipViewMode.POWER -> updatePower()
            ShipViewMode.SHIELDS -> updateShield()
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

    private fun updateShield() {
        if (tile.system is Shield) {
            val id = floorPlan.getId(tile.system)
            text.text = "$id"
        }
    }

    private fun updateCrew(options: ShipViewOptions) {
        if (tile == options.selectedCrewMan?.goal) {
            text.text = "G"
        }
    }
}