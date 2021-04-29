package org.rak.starsBetween.planets.generation

import org.rak.starsBetween.clamp
import org.rak.starsBetween.getPercent
import kotlin.math.abs

class TemperatureGenerator {
    fun generateTemperatureMap(options: PlanetOptions, heightMap: Array<IntArray>): Array<IntArray> {
        val temperatureMap = Array(heightMap.size) { IntArray(heightMap.size) }
        val minTemperature = options.temperature - options.temperatureVariance
        val maxTemperature = options.temperature + options.temperatureVariance
        val rowTemps = createRowTemps(options, temperatureMap, maxTemperature)
        return createTemperatures(options, heightMap, minTemperature, maxTemperature, rowTemps)
    }

    private fun createRowTemps(options: PlanetOptions, heightMap: Array<IntArray>, maxTemperature: Int): IntArray {
        return heightMap.indices.map { createRowTemperature(options, it, heightMap.size, maxTemperature) }.toIntArray()
    }

    private fun createTemperatures(options: PlanetOptions, heightMap: Array<IntArray>, minTemperature: Int, maxTemperature: Int, rowTemps: IntArray): Array<IntArray> {
        return heightMap.indices.map { x ->
            heightMap.indices.map { y ->
                val altitude = heightMap[x][y]
                getLocalTemperature(options.temperatureVariance, rowTemps[x], altitude, minTemperature, maxTemperature)
            }.toIntArray()
        }.toTypedArray()

    }

    private fun createRowTemperature(options: PlanetOptions, y: Int, totalRows: Int, maxTemperature: Int): Int {
        val latitude = getPercent(y, totalRows)
        return maxTemperature - (options.temperatureFactor * abs(options.temperatureVariance * latitude)).toInt()
    }

    private fun getLocalTemperature(variance: Int, columnTemperature: Int, altitude: Int, minTemperature: Int, maxTemperature: Int): Int {
        var localTemperature = columnTemperature
        localTemperature -= getAltitudeVariance(variance, altitude)
        localTemperature = clamp(localTemperature, minTemperature, maxTemperature)

        return localTemperature
    }

    private fun getAltitudeVariance(variance: Int, altitude: Int): Int {
        return if (altitude > 0) {
            variance * altitude / 100
        } else 0
    }

}
