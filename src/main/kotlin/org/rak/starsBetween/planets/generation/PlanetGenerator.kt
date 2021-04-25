package org.rak.starsBetween.planets.generation

import org.rak.starsBetween.planets.Planet

class PlanetGenerator() {
    private val heightmapGen = HeightmapGenerator()
    private val temperatureGen = TemperatureGenerator()
    private val precipitationGen = PrecipitationGenerator()
    private val biomeGen = BiomeGenerator()
    private val regionGen = RegionGenerator()
    private val mapTiler = MapTiler()
    private val debugTimer = DebugTimer()

    fun generatePlanet(options: PlanetOptions): Planet {
        debugTimer.start("Planet Generation")

        val heightMap: Array<IntArray> = heightmapGen.generateHeightMap(options)
        debugTimer.interval("Height Generation")

        mapTiler.makeImageTilable(heightMap)
        val temperatureMap = temperatureGen.generateTemperatureMap(options, heightMap)
        debugTimer.interval("Temperature Generation")

        val precipitationMap: Array<IntArray> = precipitationGen.generatePrecipitationMap(options.seed, heightMap, temperatureMap, options)
        debugTimer.interval("Precipitation Generation")

        val biomes = biomeGen.getBiomes(options.biomeType)
        debugTimer.interval("Biome Generation")

        val regions = regionGen.generateRegions(heightMap, temperatureMap, precipitationMap, biomes)
        debugTimer.interval("Region Generation")

        val planet = Planet(regions)
        debugTimer.interval("Planet Generation")
        debugTimer.elapsed("Planet Generation")
        return planet
    }
}
