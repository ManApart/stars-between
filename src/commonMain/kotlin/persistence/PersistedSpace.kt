package persistence

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import power.Engine
import shipStructor.SPACE_SYSTEM
import systems.ShipSystem

@Serializable
//@SerialName("Space")
data class PersistedSpace(val thingy: String) : PersistedSystem {

//    override fun equals(other: Any?): Boolean {
//        return this === other
//    }
    override fun toSystem(): ShipSystem {
//        return SPACE_SYSTEM
        return Engine()
    }

//    override fun hashCode(): Int {
//        return 0
//    }
}

//object SpaceSerializer : KSerializer<PersistedSpace> {
//    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("SpaceStuff", PrimitiveKind.STRING)
//
//    override fun serialize(encoder: Encoder, value: PersistedSpace) {
//        encoder.encodeString("Space")
//    }
//
//    override fun deserialize(decoder: Decoder): PersistedSpace {
//        return PersistedSpace("")
//    }
//}

