package org.rak.microStars.systems

abstract class ShipSystem(
    val name: String,
    val totalHealth: Int = 100,
    private val solid: Boolean = false
) {
    var health = totalHealth

    fun isSolid(): Boolean {
        return solid && health > 0
    }
}
