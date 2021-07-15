package ui

import com.soywiz.korio.file.std.resourcesVfs

object Resources {
    val biomes: MutableMap<String, String> = mutableMapOf()

    suspend fun init() {
        if (biomes.isEmpty()) {
            readBiomes()
        }
    }

    private suspend fun readBiomes() {
        //Doing this until resourcesVfs["biomes/"].listSimple() works
        readBiome("EarthlikeBiomes")
        readBiome("GassBiomes")
        readBiome("RockyBiomes")
        readBiome("StarBiomes")
    }

    private suspend fun readBiome(base: String) {
        biomes[base] = resourcesVfs["biomes/$base.json"].readString()
    }
}