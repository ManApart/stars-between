package planet.generation

import planet.BiomeType
import kotlin.math.min

class PlanetOptions(
    seed: Long? = null,
    density: Int? = null,

    //heightmap
    octaves: Int? = null,
    roughness: Float? = null,
    noiseScale: Float? = null,

    //temp
    temperature: Int? = null,
    temperatureVariance: Int? = null,
    temperatureFactor: Float? = null,

    //precipitation
    defaultPrecipitation: Int? = null,
    waterThreshold: Int? = null,
    biomeType: BiomeType? = null,

    ) {
    val seed = seed ?: 1234L
    val density = min(density ?: 100, 1000)
    val octaves = octaves ?: 7
    val roughness = roughness ?: 0.5f
    val noiseScale = noiseScale ?: 7f
    val temperature = temperature ?: 50
    val temperatureVariance = temperatureVariance ?: 50
    val temperatureFactor = temperatureFactor ?: 1.4f
    val defaultPrecipitation = defaultPrecipitation ?: 100
    val waterThreshold = waterThreshold ?: 0
    val biomeType = biomeType ?: BiomeType.EARTH_LIKE

}
