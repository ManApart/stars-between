package wiring

import com.soywiz.korge.service.storage.storage
import com.soywiz.korge.view.Views
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
    val json = mapper.encodeToString(PersistedFloorPlan(Game.floorPlan))
    storage["save"] = json
}

fun Views.loadGame() {
    val saveFile = storage.getOrNull("save")
    if (saveFile != null) {
        val simpleFloorPlan: PersistedFloorPlan = mapper.decodeFromString(saveFile)
        Game.floorPlan = simpleFloorPlan.toFloorPlan()
    } else {
        Game.floorPlan = Game.createFloorPlan(10)
    }
}
