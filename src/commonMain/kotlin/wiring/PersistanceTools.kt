package wiring

import com.soywiz.korio.file.std.resourcesVfs
import com.soywiz.korio.lang.toByteArray
import game.Game
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import persistence.*


const val saveName = "./saves/save.json"

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
    }
}

suspend fun saveGame() {
    val json = mapper.encodeToString(PersistedFloorPlan(Game.floorPlan))
    resourcesVfs[saveName].write(json.toByteArray())
}

suspend fun loadGame() {
    if (resourcesVfs[saveName].exists()) {
        val saveFile = resourcesVfs[saveName].readString()
        val simpleFloorPlan: PersistedFloorPlan = mapper.decodeFromString(saveFile)
        Game.floorPlan = simpleFloorPlan.toFloorPlan()
    } else {
        println("Could not find save $saveName")
    }
}
