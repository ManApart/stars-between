package org.rak.starsBetween.views.planetView

import org.rak.starsBetween.clamp
import java.awt.Color

class ColorSpectrum(private val keyFrames: Map<Int, Color>) {
    private val positions = keyFrames.keys.sorted()

    fun getColor(position: Int): Color {
        val before = getPrevious(position)
        val after = getNext(position)

        //If we're already out of range, return the last on the list
        if (before == after) {
            return keyFrames[before]!!
        }
        val blendPercent = getBlendRelativeToLowerBound(before, after, position)

        return blendColor(keyFrames[before]!!, keyFrames[after]!!, blendPercent)
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
        val validR = clamp(r.toInt(), 0, 255)
        val validG = clamp(g.toInt(), 0, 255)
        val validB = clamp(b.toInt(), 0, 255)
        return Color(validR, validG, validB)
    }

}

val altitudeSpectrum = ColorSpectrum(
    mapOf(
        -120 to Color.black,
        120 to Color.white
    )
)
