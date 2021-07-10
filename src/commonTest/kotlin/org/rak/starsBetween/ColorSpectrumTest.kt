package org.rak.starsBetween

import junit.framework.Assert.assertEquals
import org.junit.Test
import org.rak.starsBetween.planet.planetView.ColorSpectrum
import java.awt.Color

class ColorSpectrumTest {

    @Test
    fun getNext() {
        val spectrum = ColorSpectrum(listOf(0 to Color.black, 50 to Color.blue, 100 to Color.white).toMap())

        assertEquals(50, spectrum.getNext(1))
        assertEquals(50, spectrum.getNext(10))
        assertEquals(100, spectrum.getNext(51))
        assertEquals(100, spectrum.getNext(100))
        assertEquals(100, spectrum.getNext(200))
    }

    @Test
    fun getPrevious() {
        val spectrum = ColorSpectrum(listOf(0 to Color.black, 50 to Color.blue, 100 to Color.white).toMap())

        assertEquals(100, spectrum.getPrevious(200))
        assertEquals(50, spectrum.getPrevious(99))
        assertEquals(0, spectrum.getPrevious(49))
        assertEquals(0, spectrum.getPrevious(10))
        assertEquals(0, spectrum.getPrevious(0))
    }

    @Test
    fun getBlend() {
        val spectrum = ColorSpectrum(mapOf())

        assertEquals(.5f, spectrum.getBlendRelativeToLowerBound(0, 100, 50))
        assertEquals(.25f, spectrum.getBlendRelativeToLowerBound(-100, 100, 50))
        assertEquals(.75f, spectrum.getBlendRelativeToLowerBound(-100, 100, -50))
    }
}
