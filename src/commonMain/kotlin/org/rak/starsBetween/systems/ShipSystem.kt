package org.rak.starsBetween.systems

import org.rak.starsBetween.floorplan.FloorPlan
import org.rak.starsBetween.tile.SystemType
import org.rak.starsBetween.tile.Tile
import org.rak.starsBetween.views.persistence.PersistedSystem

abstract class ShipSystem(
    val name: String,
    val type: SystemType,
    val totalHealth: Int = 100,
    private val solid: Boolean = false
) {
    var health = totalHealth

    fun isSolid(): Boolean {
        return solid && health > 0
    }

    open fun tick(parent: Tile) {}

    open fun floorPlanUpdated(floorPlan: FloorPlan, parent: Tile) {}

    abstract fun persisted(): PersistedSystem
}
