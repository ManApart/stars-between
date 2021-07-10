package org.rak.starsBetween.planet

//import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.soywiz.korim.color.Colors
import kotlin.math.abs

//val DEFAULT_BIOME = Biome("DefaultBiome", "Barren Rock", Color(93, 56, 45))

//@JsonIgnoreProperties(ignoreUnknown = true)
class Biome(
    val id: String,
    private val name: String,
    color: String,

    private val altitude: Int = 0,
    private val temperature: Int = 0,
    private val precipitation: Int = 0,

    private val altitudeVariation: Int = 0,
    private val temperatureVariation: Int = 0,
    private val precipitationVariation: Int = 0,
) {
    val color = Colors[color]

    override fun toString(): String {
        return "$name a:$altitude, t:$temperature, p:$precipitation"
    }

    fun couldContain(altitude: Int, temperature: Int, precipitation: Int): Boolean {
        return altitude in (altitude - altitudeVariation)..(altitude + altitudeVariation) &&
                temperature in (temperature - temperatureVariation)..(temperature + temperatureVariation) &&
                precipitation in (precipitation - precipitationVariation)..(precipitation + precipitationVariation)
    }

    fun getDeviation(altitude: Int, temperature: Int, precipitation: Int): Int {
        return abs(this.altitude - altitude) + abs(this.temperature - temperature) + abs(this.precipitation - precipitation)
    }


}
