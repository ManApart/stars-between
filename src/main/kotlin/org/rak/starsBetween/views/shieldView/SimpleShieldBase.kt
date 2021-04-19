package org.rak.starsBetween.views.shieldView

import org.rak.starsBetween.systems.Shield

class SimpleShieldBase(
    val id: Int,
    val x: Int,
    val y: Int,
    val frequency: Int,
    val currentDesiredPower: Int,
    val totalPowerCapacity: Int,
) {
    constructor(shield: Shield, id: Int, x: Int, y: Int) : this(id, x, y, shield.frequency, shield.currentDesiredPower, shield.totalPowerCapacity)
}
