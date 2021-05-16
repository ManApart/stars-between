package org.rak.starsBetween.planet.generation.noise

private const val BASE_SCALE = .004f

class NoiseGen(
    private val seed: Long,
    private var octaves: Int = 0,
    private var roughness: Float = 0f,
    private var scale: Float = 0f
) {

    fun generateOctavedSimplexNoise(width: Int, height: Int): Array<FloatArray> {
        val totalNoise = Array(width) { FloatArray(height) }
        var layerFrequency = scale * BASE_SCALE
        var layerWeight = 1f
        for (i in 0 until octaves) {
            // Calculate single layer/octave of simplex noise, then add it to total noise
            for (x in 0 until width) {
                for (y in 0 until height) {
                    totalNoise[x][y] += SimplexNoise.noise((x * layerFrequency).toDouble(), (y * layerFrequency).toDouble(), seed.toDouble()).toFloat() * layerWeight
                }
            }

            // Increase variables with each incrementing octave
            layerFrequency *= 2f
            layerWeight *= roughness
        }
        return totalNoise
    }

    fun generateOctavedSimplexNoiseAt(x: Int, y: Int): Float {
        var totalNoise = 0f
        var layerFrequency = scale * BASE_SCALE
        var layerWeight = 1f
        for (i in 0 until octaves) {
            // Calculate single layer/octave of simplex noise, then add it to total noise
            totalNoise += SimplexNoise.noise((x * layerFrequency).toDouble(), (y * layerFrequency).toDouble(), seed.toDouble()).toFloat() * layerWeight

            // Increase variables with each incrementing octave
            layerFrequency *= 2f
            layerWeight *= roughness
        }
        return totalNoise
    }

    fun octavedNoise(x: Int, y: Int): Float {
        var noiseSum = 0f
        var layerFrequency = scale * BASE_SCALE
        var layerWeight = 1f
        for (octave in 0 until octaves) {
            noiseSum += (SimplexNoise.noise((x * layerFrequency).toDouble(), (y * layerFrequency).toDouble(), seed.toDouble()) * layerWeight).toFloat()
            layerFrequency *= 2f
            layerWeight *= roughness
        }
        return noiseSum
    }
}
