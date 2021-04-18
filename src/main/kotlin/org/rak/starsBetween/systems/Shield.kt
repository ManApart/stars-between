package org.rak.starsBetween.systems

import org.rak.starsBetween.power.Powerable
import org.rak.starsBetween.tile.SystemType
import org.rak.starsBetween.tile.Tile
import org.rak.starsBetween.views.persistence.PersistedSystem

class Shield(
    health: Int = 100,
    //1-10
    val frequency: Int = 1,
    //How many tiles this shield protects
    val radius: Int = 1,
    //How much damage is blocked per unit of power
    val powerToStrength: Float = 1f,
    //The min power this shield can have before it turns off
    val minPowerConsumedPerTick: Int = 100,
    //The max power this shield can consume per tick (and turn into shield strength
    val maxPowerConsumedPerTick: Int = 100,
    //The total power that the shield can 'bank'
    override val totalPowerCapacity: Int = 100,
) : ShipSystem("Shield", SystemType.SHIELD, health, true), Powerable {
    //Current 'banked' power
    override var power = totalPowerCapacity
    override var lastReceivedPowerFrom: Tile? = null
    //Current amount of power to pull each tick
    var currentPowerPull = 0
    //Strength of shield; based on amount of power pulled last tick * shield strength factor
    var shieldStrength = 0f

    override val powerConsumedPerTick: Int; get() = currentPowerPull

    override fun persisted(): PersistedSystem {
        return PersistedShield(this)
    }
}
