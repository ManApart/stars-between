package org.rak.starsBetween.planets

import org.rak.starsBetween.clamp
import org.rak.starsBetween.getPercent
import kotlin.math.abs

class TemperatureGenerator(
    private val temperature: Int = 50,
    private val temperatureVariance: Int = 50,
    private val TEMPERATURE_FACTOR: Float = 1.4f
) {
    fun generateTemperatureMap(heightMap: Array<IntArray>): Array<IntArray> {
        val temperatureMap = initializeTemperatureMap(heightMap)
        val minTemperature = temperature - temperatureVariance
        val maxTemperature = temperature + temperatureVariance
        populateTemperatures(heightMap, temperatureMap, minTemperature, maxTemperature)

        return temperatureMap
    }

    private fun initializeTemperatureMap(heightMap: Array<IntArray>): Array<IntArray> {
        val density = heightMap.size
        return Array(density) { IntArray(density) }
    }

    private fun populateTemperatures(heightMap: Array<IntArray>, temperatureMap: Array<IntArray>, minTemperature: Int, maxTemperature: Int) {
        for (y in temperatureMap.indices) {
            val rowTemperature = createRowTemperature(y, temperatureMap, maxTemperature)
            populateColumnTemperatures(heightMap, temperatureMap, y, rowTemperature, minTemperature, maxTemperature)
        }
    }

    private fun createRowTemperature(y: Int, temperatureMap: Array<IntArray>, maxTemperature: Int): Int {
        val latitude = getPercent(y, temperatureMap.size)
        var rowTemperature = maxTemperature
        rowTemperature -= (TEMPERATURE_FACTOR * abs(temperatureVariance * latitude)).toInt()
        return rowTemperature
    }

    private fun populateColumnTemperatures(heightMap: Array<IntArray>, temperatureMap: Array<IntArray>, y: Int, rowTemperature: Int, minTemperature: Int, maxTemperature: Int) {
        for (x in temperatureMap.indices) {
            val altitude = heightMap[x][y]
            val localTemperature = getLocalTemperature(rowTemperature, altitude, minTemperature, maxTemperature)

            temperatureMap[x][y] = localTemperature
        }
    }

    private fun getLocalTemperature(columnTemperature: Int, altitude: Int, minTemperature: Int, maxTemperature: Int): Int {
        var localTemperature = columnTemperature
        localTemperature -= getAltitudeVariance(altitude)
        localTemperature = clamp(localTemperature, minTemperature, maxTemperature)

        return localTemperature
    }

    private fun getAltitudeVariance(altitude: Int): Int {
        return if (altitude > 0) {
            temperatureVariance * altitude / 100
        } else 0
    }

}
