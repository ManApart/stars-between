package planet.generation

import planet.BiomeType

class PlanetOptions(
    val seed: Long = 1234L,
    val density: Int = 100,
    val octaves: Int = 7,
    val roughness: Float = 0.5f,
    val noiseScale: Float = 7.0f,
    val temperature: Int = 50,
    val temperatureVariance: Int = 50,
    val temperatureFactor: Double = 1.4,
    val defaultPrecipitation: Int = 100,
    val waterThreshold: Int = 0,
    val biomeType: BiomeType = BiomeType.EARTH_LIKE
)