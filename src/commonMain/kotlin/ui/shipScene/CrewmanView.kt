package ui.shipScene

import com.soywiz.korge.view.Circle
import com.soywiz.korge.view.position
import crew.CrewMan

class CrewmanView(
    private val man: CrewMan,
    private val circle: Circle
) {
    fun tick() {
        val pos = man.tile.position
        circle.position(pos.x * TILE_SIZE, pos.y * TILE_SIZE)
    }
}