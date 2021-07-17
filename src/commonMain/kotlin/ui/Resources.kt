package ui

import com.soywiz.korio.file.std.resourcesVfs

object Resources {
    var inited = false
    val biomes: MutableMap<String, String> = mutableMapOf()

    suspend fun init() {
        if (!inited) {
            inited = true
            readBiomes()
        }
    }

    private suspend fun readBiomes() {
        //Doing this until resourcesVfs["biomes/"].listSimple() works on js
        readBiome("EarthlikeBiomes")
        readBiome("GassBiomes")
        readBiome("RockyBiomes")
        readBiome("StarBiomes")
    }

    private suspend fun readBiome(base: String) {
        biomes[base] = resourcesVfs["biomes/$base.json"].readString()
    }
}