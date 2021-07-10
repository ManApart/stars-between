package persistence

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

class PersistedSystemSerializer : KSerializer<PersistedSystem> {
    override val descriptor: SerialDescriptor = PersistedEngine.serializer().descriptor
    override fun deserialize(decoder: Decoder): PersistedSystem {
        return decoder.decodeSerializableValue(PersistedEngine.serializer())
    }

    override fun serialize(encoder: Encoder, value: PersistedSystem) {
        if (value is PersistedEngine){
            encoder.encodeSerializableValue(PersistedEngine.serializer(), value)
        }
    }
}