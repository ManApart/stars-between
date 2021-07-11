package ui

import com.soywiz.korio.file.baseName
import com.soywiz.korio.file.std.resourcesVfs
import kotlinx.coroutines.flow.map

object Resources {
    var biomes: Map<String, String> = mapOf()

    suspend fun init() {
        if (biomes.isEmpty()) {
            biomes = readBiomes()
        }
    }

    private suspend fun readBiomes(): Map<String, String> {
       return resourcesVfs["biomes/"].listSimple().associate {
            it.baseName to it.readString()
        }
    }
}