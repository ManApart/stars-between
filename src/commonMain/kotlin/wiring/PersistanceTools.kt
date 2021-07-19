package wiring

import com.soywiz.korge.service.storage.storage
import com.soywiz.korge.view.Views
import floorplan.Ship
import floorplan.createFloorPlan
import game.Game
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import persistence.*


val mapper = Json {
    serializersModule = SerializersModule {
        polymorphic(PersistedSystem::class) {
            subclass(PersistedEngine::class, PersistedEngine.serializer())
            subclass(PersistedFloor::class, PersistedFloor.serializer())
            subclass(PersistedShield::class, PersistedShield.serializer())
            subclass(PersistedSpace::class, PersistedSpace.serializer())
            subclass(PersistedVent::class, PersistedVent.serializer())
            subclass(PersistedWall::class, PersistedWall.serializer())
            subclass(PersistedWire::class, PersistedWire.serializer())
        }
        ignoreUnknownKeys = true
    }
}

fun Views.saveGame() {
    val floorPlan = mapper.encodeToString(PersistedFloorPlan(Game.ship.floorPlan))
    val crew = mapper.encodeToString(Game.ship.crew.values.map { PersistedCrewMan(it) })
    storage["floorPlan"] = floorPlan
    storage["crew"] = crew

    println("Saved!")
}

fun Views.loadGame() {
    val floorPlanSave = storage.getOrNull("floorPlan")
    val floorPlan = if (floorPlanSave != null) {
        val simpleFloorPlan: PersistedFloorPlan = mapper.decodeFromString(floorPlanSave)
        val floorPlan = simpleFloorPlan.toFloorPlan()
        floorPlan
    } else {
        createFloorPlan(10)
    }

    val crewSave = storage.getOrNull("crew")
    val crew = if (crewSave != null) {
        val simpleCrew: List<PersistedCrewMan> = mapper.decodeFromString(crewSave)
        simpleCrew
            .mapIndexed { id, man -> man.toCrewMan(id, floorPlan) }
            .associateBy { it.id }
    } else {
        mapOf()
    }.toMutableMap()

    Game.ship = Ship(floorPlan, crew)
}
