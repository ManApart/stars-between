package planet.generation

import planet.BiomeType

class PlanetOptions(
    var seed: Double = 1234.0,
    var density: Int = 100,

    //heightmap
    var octaves: Int = 7,
    var roughness: Float = 0.5f,
    var noiseScale: Float = 7.0f,

    //temperature
    var temperature: Int = 50,
    var temperatureVariance: Int = 50,
    var temperatureFactor: Double = 1.4,

    //precipitation
    var defaultPrecipitation: Int = 100,
    var waterThreshold: Int = 0,
    var biomeType: BiomeType = BiomeType.EARTH_LIKE
)
