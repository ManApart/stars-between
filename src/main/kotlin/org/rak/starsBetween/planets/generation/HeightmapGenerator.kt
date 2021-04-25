package org.rak.starsBetween.planets.generation

import org.rak.starsBetween.planets.generation.noise.NoiseGen

class HeightmapGenerator() {

    fun generateHeightMap(options: PlanetOptions): Array<IntArray> {
        val density = (options.density / options.scale).toInt()
        val noiseGen = createNoiseGen(options)
        val heightMap = Array(density) { IntArray(density) }
        for (x in 0 until density) {
            for (y in 0 until density) {
                heightMap[x][y] = (100 * noiseGen.generateOctavedSimplexNoiseAt(x, y)).toInt()
            }
        }
        return heightMap
    }

    private fun createNoiseGen(options: PlanetOptions): NoiseGen {
        val adjustedScale = options.scale * options.noiseScale
        return NoiseGen(options.seed, options.octaves, options.roughness, adjustedScale)
    }

}
