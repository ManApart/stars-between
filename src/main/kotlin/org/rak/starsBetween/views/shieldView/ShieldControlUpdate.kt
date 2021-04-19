package org.rak.starsBetween.views.shieldView

import org.rak.starsBetween.systems.Shield

class ShieldControlUpdate(
    val id: Int,
    val frequency: Int,
    val currentDesiredPower: Int
) {
    constructor(shield: Shield, id: Int) : this(id, shield.frequency, shield.currentDesiredPower)
}
