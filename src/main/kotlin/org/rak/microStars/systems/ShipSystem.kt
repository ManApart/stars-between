package org.rak.microStars.systems

import org.rak.microStars.tile.SystemType
import org.rak.microStars.tile.Tile
import org.rak.microStars.views.persistence.PersistedSystem

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

    abstract fun persisted(): PersistedSystem
}
