package org.rak.starsBetween.planets

import org.rak.starsBetween.planets.noise.NoiseGen

class HeightmapGenerator(
    private val octaves: Int = 7,
    private val roughness: Float = 0.5f,
    private val noiseScale: Float = 1f
) {

    fun generateHeightMap(seed: Long, density: Int, planetScale: Float): Array<IntArray> {
        val noiseGen = createNoiseGen(seed, planetScale)
        val heightMap = Array(density) { IntArray(density) }
        for (x in 0 until density) {
            for (y in 0 until density) {
                heightMap[x][y] = (100 * noiseGen.generateOctavedSimplexNoiseAt(x, y)).toInt()
            }
        }
        return heightMap
    }

    private fun createNoiseGen(seed: Long, scale: Float): NoiseGen {
        val adjustedScale = scale * noiseScale
        return NoiseGen(seed, octaves, roughness, adjustedScale)
    }

}
