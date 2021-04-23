package org.rak.starsBetween.planets

class PlanetOptions(
    seed: Long? = null,
    density: Int? = null,
    scale: Float? = null,

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
    biomeTypes: String? = null,

    ) {
    val seed = seed ?: 1234L
    val density = density ?: 500
    val scale = scale ?: 1f
    val octaves = octaves ?: 7
    val roughness = roughness ?: 0.5f
    val noiseScale = noiseScale ?: 1f
    val temperature = temperature ?: 50
    val temperatureVariance = temperatureVariance ?: 50
    val temperatureFactor = temperatureFactor ?: 1.4f
    val defaultPrecipitation = defaultPrecipitation ?: 100
    val waterThreshold = waterThreshold ?: 100
    val biomeTypes = biomeTypes ?: "EarthlikeBiomes"

}
