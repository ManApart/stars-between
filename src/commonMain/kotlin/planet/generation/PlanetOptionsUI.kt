package planet.generation

import planet.BiomeType

class PlanetOptionsUI(
    var seed: Double = 1234.0,
    var density: Double = 100.0,

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
) {
    fun toOptions(): PlanetOptions {
        return PlanetOptions(
            seed.toLong(),
            density.toInt(),
            octaves,
            roughness,
            noiseScale,
            temperature,
            temperatureVariance,
            temperatureFactor,
            defaultPrecipitation,
            waterThreshold,
            biomeType
        )
    }
}
