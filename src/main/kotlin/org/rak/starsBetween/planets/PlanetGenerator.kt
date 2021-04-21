package org.rak.starsBetween.planets

class PlanetGenerator(private val density: Int = 500, private val scale: Float = 1f) {
    private val heightmapGen = HeightmapGenerator()
    private val temperatureGen = TemperatureGenerator()
    private val precipitationGen = PrecipitationGenerator()
    private val mapTiler = MapTiler()
    private val debugTimer = DebugTimer()
    private val scaledDensity = (density / scale).toInt()

    fun generatePlanet(seed: Long): Planet {
        debugTimer.start("Planet Generation")

        val heightMap: Array<IntArray> = heightmapGen.generateHeightMap(seed, scaledDensity, scale)
        debugTimer.interval("Height Generation")

        mapTiler.makeImageTilable(heightMap)
        val temperatureMap = temperatureGen.generateTemperatureMap(heightMap)
        debugTimer.interval("Temperature Generation")

        val precipitationMap: Array<IntArray> = precipitationGen.generatePrecipitationMap(seed, heightMap, temperatureMap)
        debugTimer.interval("Precipitation Generation")

//        val biomes: BiomeCollection = biomeGenerator.generateBiomes()
//        debugTimer.interval("Biome Generation")

//        val regionGenerator = RegionGenerator(heightMap, temperatureMap, percipitationMap, biomes)
//        val regions: Grid<Region> = regionGenerator.generateRegions()
//        debugTimer.interval("Region Generation")

        val planet = Planet()
//        planet.setRegionGrid(regions)
        debugTimer.interval("Planet Generation")
        debugTimer.elapsed("Planet Generation")
        return planet
    }
}
