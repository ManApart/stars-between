package org.rak.starsBetween.planets.generation

import org.rak.starsBetween.clamp
import org.rak.starsBetween.getPercent
import kotlin.math.abs

class TemperatureGenerator() {
    fun generateTemperatureMap(options: PlanetOptions, heightMap: Array<IntArray>): Array<IntArray> {
        val temperatureMap = initializeTemperatureMap(heightMap)
        val minTemperature = options.temperature - options.temperatureVariance
        val maxTemperature = options.temperature + options.temperatureVariance
        populateTemperatures(options, heightMap, temperatureMap, minTemperature, maxTemperature)

        return temperatureMap
    }

    private fun initializeTemperatureMap(heightMap: Array<IntArray>): Array<IntArray> {
        val density = heightMap.size
        return Array(density) { IntArray(density) }
    }

    private fun populateTemperatures(options: PlanetOptions, heightMap: Array<IntArray>, temperatureMap: Array<IntArray>, minTemperature: Int, maxTemperature: Int) {
        for (y in temperatureMap.indices) {
            val rowTemperature = createRowTemperature(options, y, temperatureMap, maxTemperature)
            populateColumnTemperatures(heightMap, temperatureMap, y, rowTemperature, minTemperature, maxTemperature, options.temperatureVariance)
        }
    }

    private fun createRowTemperature(options: PlanetOptions, y: Int, temperatureMap: Array<IntArray>, maxTemperature: Int): Int {
        val latitude = getPercent(y, temperatureMap.size)
        var rowTemperature = maxTemperature
        rowTemperature -= (options.temperatureFactor * abs(options.temperatureVariance * latitude)).toInt()
        return rowTemperature
    }

    private fun populateColumnTemperatures(heightMap: Array<IntArray>, temperatureMap: Array<IntArray>, y: Int, rowTemperature: Int, minTemperature: Int, maxTemperature: Int, variance: Int) {
        for (x in temperatureMap.indices) {
            val altitude = heightMap[x][y]
            val localTemperature = getLocalTemperature(variance, rowTemperature, altitude, minTemperature, maxTemperature)

            temperatureMap[x][y] = localTemperature
        }
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
