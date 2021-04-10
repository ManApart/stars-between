package org.rak.microStars.wiring

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.rak.microStars.game.Game
import org.rak.microStars.views.persistence.PersistedFloorPlan
import java.io.File

const val directoryName = "./saves/"
const val saveName = "./saves/save.json"

fun saveGame() {
    val json = jacksonObjectMapper().writeValueAsString(PersistedFloorPlan(Game.floorPlan))
    val directory = File(directoryName)
    if (!directory.exists()) {
        directory.mkdirs()
    }
    File(saveName).printWriter().use { out ->
        out.println(json)
    }
}

fun loadGame() {
    val stream = File(saveName)
    if (stream.exists()) {
        val simpleFloorPlan: PersistedFloorPlan = jacksonObjectMapper().readValue(stream)
        Game.floorPlan = simpleFloorPlan.toFloorPlan()
    } else {
        println("Could not find save $saveName")
    }
}
