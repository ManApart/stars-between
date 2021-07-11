package planet.generation

import kotlinx.serialization.decodeFromString
import planet.Biome
import planet.BiomeType
import ui.Resources
import wiring.mapper

class BiomeGenerator {
    private val biomes = parseBiomes(Resources.biomes)

    private fun parseBiomes(biomeFiles: Map<String, String>): Map<String, List<Biome>> {
        return biomeFiles.entries.associate { (fileName, fileContents) ->
           fileName.replace(".json", "") to mapper.decodeFromString(fileContents)
        }
    }

    fun getBiomes(type: BiomeType) : List<Biome> {
        return biomes[type.fileName] ?: listOf()
    }

}
