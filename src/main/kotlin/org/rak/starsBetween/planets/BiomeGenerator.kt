package org.rak.starsBetween.planets

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.rak.starsBetween.wiring.ResourceHelper

class BiomeGenerator {
    val biomes = parseBiomes()
    private fun parseFile(path: String): List<Biome> = jacksonObjectMapper().readValue(this::class.java.getResourceAsStream(path))


    private fun parseBiomes(): Map<String, Map<String, Biome>> {
        val biomePath = "/biomes"
        return ResourceHelper().getResourceFiles(biomePath, true)
            .associateWith { fileName ->
                parseFile(fileName).associateBy { it.id }
            }
    }
}
