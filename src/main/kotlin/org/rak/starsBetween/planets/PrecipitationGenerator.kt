package org.rak.starsBetween.planets

import org.rak.starsBetween.clamp
import org.rak.starsBetween.getPercent
import kotlin.math.abs

private const val ALTITUDE_FACTOR = 0.9f

class PrecipitationGenerator(
    private val defaultPrecipitationLevel: Int = 100,
    private val waterThreshold: Int = 0
) {
    private val desertBandGenerator = DesertBandGenerator()

    fun generatePrecipitationMap(seed: Long, heightMap: Array<IntArray>, temperatureMap: Array<IntArray>): Array<IntArray> {
        val bands = desertBandGenerator.generateDesertBands(seed)
        val precipitationMap = initializePrecipitationMap(heightMap)
        return populatePrecipitation(heightMap, temperatureMap, precipitationMap, bands)
    }

    private fun initializePrecipitationMap(heightMap: Array<IntArray>): Array<IntArray> {
        val density = heightMap.size
        return Array(density) { IntArray(density) }
    }

    private fun populatePrecipitation(heightMap: Array<IntArray>, temperatureMap: Array<IntArray>, precipitationMap: Array<IntArray>, bands: List<DesertBand>, ): Array<IntArray> {
        for (y in precipitationMap.indices) {
            val latitude = getPercent(y, precipitationMap.size)

            for (x in precipitationMap.indices) {
                val altitude = heightMap[x][y]
                val temperature = temperatureMap[x][y]

                precipitationMap[x][y] = generatePrecipitation(bands, altitude, temperature, latitude)
            }
        }
        return precipitationMap
    }

    private fun generatePrecipitation(bands: List<DesertBand>, altitude: Int, temperature: Int, latitude: Float): Int {
        if (altitude < waterThreshold) {
            return defaultPrecipitationLevel
        }
        var precipitation = defaultPrecipitationLevel
        precipitation -= getAmountLessPrecipitationDueToAltitude(altitude)
        precipitation -= getAmountLessPrecipitationDueToDeserts(bands, latitude, precipitation).toInt()

        precipitation = clamp(precipitation, 0, 100)

        return precipitation
    }

    private fun getAmountLessPrecipitationDueToAltitude(altitude: Int): Int {
        val adjustedAltitude = altitude - waterThreshold
        return (adjustedAltitude * ALTITUDE_FACTOR).toInt()
    }

    private fun getAmountLessPrecipitationDueToDeserts(bands: List<DesertBand>, latitude: Float, precipitation: Int): Float {
        return precipitation * getDesertFactor(bands, latitude)
    }

    /**
     * A 0-1 scale where 0 means deserts have no affect, and 1 meaning this region is completely desert
     */
    private fun getDesertFactor(bands: List<DesertBand>, latitude: Float): Float {
        var desertFactor = 0f
        for (band in bands) {
            desertFactor += getPercentFromBand(band, latitude)
        }

        return clamp(desertFactor, 0f, 1f)
    }

    private fun getPercentFromBand(band: DesertBand, latitude: Float): Float {
        val distanceFromBandCenter = abs(band.latitude - latitude)
        if (distanceFromBandCenter < band.width) {
            val distanceRelativeToWidth = band.width - distanceFromBandCenter

            return distanceRelativeToWidth / band.width
        }
        return 0f
    }

}
