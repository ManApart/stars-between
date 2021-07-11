package planet

import com.soywiz.korim.color.Colors
import com.soywiz.korim.color.RGBA
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlin.math.abs

val DEFAULT_BIOME = Biome("DefaultBiome", "Barren Rock", RGBA(93, 56, 45).hexString)

@Serializable
class Biome(
    val id: String,
    private val name: String,
    @SerialName("color")
    private val colorString: String,

    private val altitude: Int = 0,
    private val temperature: Int = 0,
    private val precipitation: Int = 0,

    private val altitudeVariation: Int = 0,
    private val temperatureVariation: Int = 0,
    private val precipitationVariation: Int = 0,
) {
    @Transient
    val color = Colors[colorString]

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
