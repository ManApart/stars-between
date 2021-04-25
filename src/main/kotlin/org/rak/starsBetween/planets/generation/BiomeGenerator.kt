package org.rak.starsBetween.planets.generation

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.rak.starsBetween.planets.Biome
import org.rak.starsBetween.planets.BiomeType
import org.rak.starsBetween.wiring.ResourceHelper

class BiomeGenerator {
    private val biomes = parseBiomes()
    private fun parseFile(path: String): List<Biome> = jacksonObjectMapper().readValue(this::class.java.getResourceAsStream(path))

    private fun parseBiomes(): Map<String, List<Biome>> {
        val biomePath = "/biomes"
        return ResourceHelper().getResourceFiles(biomePath, true)
            .associate { fileName ->
                fileName.replace("/biomes/", "").replace(".json", "") to parseFile(fileName)
            }
    }

    fun getBiomes(type: BiomeType) : List<Biome> {
        return biomes[type.fileName] ?: listOf()
    }

}
