import com.soywiz.korim.color.Colors
import planet.planetView.ColorSpectrum
import kotlin.test.Test
import kotlin.test.assertEquals

class ColorSpectrumTest {

    @Test
    fun getNext() {
        val spectrum = ColorSpectrum(listOf(0 to Colors.BLACK, 50 to Colors.BLUE, 100 to Colors.WHITE).toMap())

        assertEquals(50, spectrum.getNext(1))
        assertEquals(50, spectrum.getNext(10))
        assertEquals(100, spectrum.getNext(51))
        assertEquals(100, spectrum.getNext(100))
        assertEquals(100, spectrum.getNext(200))
    }

    @Test
    fun getPrevious() {
        val spectrum = ColorSpectrum(listOf(0 to Colors.BLACK, 50 to Colors.BLUE, 100 to Colors.WHITE).toMap())

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
