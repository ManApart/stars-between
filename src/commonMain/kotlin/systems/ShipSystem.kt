package systems

import floorplan.FloorPlan
import tile.SystemType
import tile.Tile
import persistence.PersistedSystem

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

    override fun toString(): String {
        return "$name: $type, $health/$totalHealth"
    }
}
