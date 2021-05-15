package org.rak.starsBetween.planets.generation

import org.rak.starsBetween.planets.generation.noise.NoiseGen

class HeightmapGenerator() {

    fun generateHeightMap(options: PlanetOptions): Array<IntArray> {
        val noiseGen = createNoiseGen(options)
        val heightMap = Array(options.density) { IntArray(options.density) }
        for (x in 0 until options.density) {
            for (y in 0 until options.density) {
                heightMap[x][y] = (100 * noiseGen.generateOctavedSimplexNoiseAt(x, y)).toInt()
            }
        }
        return heightMap
    }

    private fun createNoiseGen(options: PlanetOptions): NoiseGen {
        return NoiseGen(options.seed, options.octaves, options.roughness, options.noiseScale)
    }

}
