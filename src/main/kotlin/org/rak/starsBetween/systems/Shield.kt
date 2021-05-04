package org.rak.starsBetween.systems

import org.rak.starsBetween.floorplan.FloorPlan
import org.rak.starsBetween.power.Powerable
import org.rak.starsBetween.tile.SystemType
import org.rak.starsBetween.tile.Tile
import org.rak.starsBetween.views.persistence.PersistedSystem
import kotlin.math.min

class Shield(
    health: Int = 100,
    //1-10
    var frequency: Int = 1,
    //How many tiles this shield protects
    val radius: Int = 1,
    //How much damage is blocked per unit of power
    val powerToStrength: Float = 1f,
    //The min power this shield can have before it turns off
    val minPowerConsumedPerTick: Int = 1,
    //The max power this shield can consume per tick (and turn into shield strength
    val maxPowerConsumedPerTick: Int = 100,
    //The total power that the shield can 'bank'
    override val totalPowerCapacity: Int = 100,
) : ShipSystem("Shield", SystemType.SHIELD, health, true), Powerable {
    //Current 'banked' power
    override var power = totalPowerCapacity
    override var lastReceivedPowerFrom: Tile? = null

    //Current amount of power to pull each tick
    var currentDesiredPower = 0

    //Strength of shield; based on amount of power pulled last tick * shield strength factor
    var shieldStrength = 0f

    private var tilesToShield = listOf<Tile>()

    override val powerConsumedPerTick: Int; get() = currentDesiredPower

    override fun persisted(): PersistedSystem {
        return PersistedShield(this)
    }

    override fun tick(parent: Tile) {
        val powerPulled = min(currentDesiredPower, power)
        if (powerPulled < minPowerConsumedPerTick) {
            shieldStrength = 0f
        } else {
            shieldStrength = powerToStrength * powerPulled
            power -= powerPulled
        }
    }

    override fun floorPlanUpdated(floorPlan: FloorPlan, parent: Tile) {
        tilesToShield = floorPlan.getNeighbors(parent, radius)
    }

    fun getProtectedTiles(): List<Tile> {
        return if (shieldStrength > 0) {
            listOf()
        } else {
            tilesToShield
        }
    }
}
