package org.rak.starsBetween.planets

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.awt.Color

//val DEFAULT_BIOME = Biome("DefaultBiome", "Barren Rock", Color(93, 56, 45))

@JsonIgnoreProperties(ignoreUnknown = true)
class Biome(
    val id: String,
    private val name: String,
//    private val color: Color,

    private val altitude: Int = 0,
    private val temperature: Int = 0,
    private val precipitation: Int = 0,

    private val altitudeVariation: Int = 0,
    private val temperatureVariation: Int = 0,
    private val precipitationVariation: Int = 0,
) {

    override fun toString(): String {
        return "$name a:$altitude, t:$temperature, p:$precipitation"
    }
}
