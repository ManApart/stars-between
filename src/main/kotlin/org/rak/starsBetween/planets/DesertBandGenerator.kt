package org.rak.starsBetween.planets

import org.rak.starsBetween.randRange

private const val WIDTH_MINIMUM = 10
private const val WIDTH_MAXIMUM = 40
private const val LATITUDE_MINIMUM = -100
private const val LATITUDE_MAXIMUM = 100

class DesertBandGenerator {


    fun generateDesertBands(seed: Long): List<DesertBand> {
        val numberOfBands = randRange(seed, 0, 3)
        return (0 until numberOfBands).map { generateBand(seed) }
    }

    private fun generateBand(seed: Long): DesertBand {
        val latitude = randRange(seed, LATITUDE_MINIMUM, LATITUDE_MAXIMUM) / 100f
        val width = randRange(seed, WIDTH_MINIMUM, WIDTH_MAXIMUM) / 100f
        return DesertBand(latitude, width)
    }

}
