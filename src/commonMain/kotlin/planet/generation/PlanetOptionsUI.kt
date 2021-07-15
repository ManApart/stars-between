package planet.generation

import planet.BiomeType

class PlanetOptionsUI(
    var seed: Double = 1234.0,
    var density: Double = 100.0,
    var biomeType: BiomeType = BiomeType.EARTH_LIKE,

    //heightmap
    var octaves: Double = 7.0,
    var roughness: Double = 0.5,
    var noiseScale: Double = 7.0,

    //temperature
    var temperature: Double = 50.0,
    var temperatureVariance: Double = 50.0,
    var temperatureFactor: Double = 1.4,

    //precipitation
    var defaultPrecipitation: Double = 100.0,
    var waterThreshold: Double = 0.0
) {
    fun toOptions(): PlanetOptions {
        return PlanetOptions(
            seed.toLong(),
            density.toInt(),
            octaves.toInt(),
            roughness.toFloat(),
            noiseScale.toFloat(),
            temperature.toInt(),
            temperatureVariance.toInt(),
            temperatureFactor,
            defaultPrecipitation.toInt(),
            waterThreshold.toInt(),
            biomeType
        )
    }
}
