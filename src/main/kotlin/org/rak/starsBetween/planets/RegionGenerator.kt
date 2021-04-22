package org.rak.starsBetween.planets

class RegionGenerator {

    fun generateRegions(
        heightMap: Array<IntArray>,
        temperatureMap: Array<IntArray>,
        precipitationMap: Array<IntArray>,
        biomes: List<Biome>
    ): List<List<Region>> {
        val density: Int = heightMap.size
        return (0 until density).map { y ->
            (0 until density).map { x ->
                val altitude = heightMap[x][y]
                val temperature = temperatureMap[x][y]
                val precipitation = precipitationMap[x][y]
                val biome = findBiome(biomes, altitude, temperature, precipitation)

                Region(altitude, temperature, precipitation, biome)
            }
        }
    }

    private fun findBiome(biomes: List<Biome>, altitude: Int, temperature: Int, precipitation: Int): Biome {
        return biomes
            .filter { it.couldContain(altitude, temperature, precipitation) }
            .minByOrNull { it.getDeviation(altitude, temperature, precipitation) }
            ?: biomes.first()
    }


}
