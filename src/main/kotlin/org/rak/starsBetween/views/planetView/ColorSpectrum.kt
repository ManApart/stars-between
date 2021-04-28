package org.rak.starsBetween.views.planetView

import org.rak.starsBetween.clamp
import java.awt.Color
import kotlin.math.roundToInt

class ColorSpectrum(private val keyFrames: Map<Int, Color>) {
    private val positions = keyFrames.keys.sorted()

    fun getColor(position: Int): Color {
        val before = getPrevious(position)
        val after = getNext(position)

        return when {
            before == after -> keyFrames[before]!! //If we're already out of range, return the last on the list
            position == before -> keyFrames[before]!!
            position == after -> keyFrames[after]!!
            else -> {
                val blendPercent = getBlendRelativeToLowerBound(before, after, position)
                blendColor(keyFrames[before]!!, keyFrames[after]!!, blendPercent)
            }
        }


    }

    fun getNext(position: Int): Int {
        return positions.firstOrNull { it > position } ?: positions.last()
    }

    fun getPrevious(position: Int): Int {
        return positions.sortedDescending().firstOrNull { it < position } ?: positions.first()
    }

    fun getBlendRelativeToLowerBound(lowerBound: Int, upperBound: Int, position: Int): Float {
        val distanceBetweenBounds = (upperBound - lowerBound).toFloat()
        var progress = position - lowerBound
        progress = clamp(progress.toFloat(), 0f, distanceBetweenBounds).toInt()

        val percentRelativeToUpperBound = progress / distanceBetweenBounds
//		System.out.println("B: " + before.getPosition() + ", P: " + position + ", A: " + after.getPosition() + ", P: " + percent);
        return 1 - percentRelativeToUpperBound
    }

    private fun blendColor(color: Color, color2: Color, desiredOpacity: Float): Color {
        val opacity = clamp(desiredOpacity, 0f, 1f)
        val opacity2 = 1 - opacity

        val r = color.red * opacity + color2.red * opacity2
        val g = color.green * opacity + color2.green * opacity2
        val b = color.blue * opacity + color2.blue * opacity2

        return createValidColor(r, g, b)
    }

    private fun createValidColor(r: Float, g: Float, b: Float): Color {
        val validR = clamp(r.roundToInt(), 0, 255)
        val validG = clamp(g.roundToInt(), 0, 255)
        val validB = clamp(b.roundToInt(), 0, 255)
        return Color(validR, validG, validB)
    }

}

val altitudeSpectrum = ColorSpectrum(
    mapOf(
        -120 to Color.black,
        120 to Color.white
    )
)

val precipitationSpectrum = ColorSpectrum(
    mapOf(
        0 to Color(1f, 1f, .99f),
        20 to Color(1f, 1f, .6f),
        50 to Color(.2f, .8f, 0f),
        100 to Color(.1f, 0f, .5f)
    )
)

val temperatureSpectrum = ColorSpectrum(
    mapOf(
        -100 to Color.black,
        0 to Color(0f, 0f, 1f),
        0 to Color(0, 0, 255),
        20 to Color(54, 120, 204),
        50 to Color(105, 255, 14),
        100 to Color(236, 230, 168),
        200 to Color(231, 37, 1),
        500 to Color(255, 255, 219)
    )
)

val satelliteSpectrum = ColorSpectrum(
    mapOf(
        -120 to Color(0.0f, 0.19607843458652496f, 0.5882353186607361f),
        0 to Color(0.0f, 0.3921568691730499f, 0.7843137383460999f),
        5 to Color(0.0784313753247261f, 0.3921568691730499f, 0.19607843458652496f),
        10 to Color(0.0784313753247261f, 0.3921568691730499f, 0f),
        20 to Color(0.19607843458652496f, 0.5882353186607361f, 0f),
        60 to Color(0.19607843458652496f, 0.7843137383460999f, 0f),
        80 to Color.gray,
        120 to Color.white
    )
)
