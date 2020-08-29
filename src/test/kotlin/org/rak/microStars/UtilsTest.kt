package org.rak.microStars

import org.junit.Assert.assertEquals
import org.junit.Test

class UtilsTest {

    @Test
    fun arraysToMap() {
        val expected = mapOf(
            0 to mapOf(0 to 1, 1 to 2, 2 to 3),
            1 to mapOf(0 to 4, 1 to 5, 2 to 6),
            2 to mapOf(0 to 7, 1 to 8, 2 to 9)
        )

        val actual = arrayOf(
            arrayOf(1, 2, 3),
            arrayOf(4, 5, 6),
            arrayOf(7, 8, 9)
        ).toMap()

        assertEquals(expected, actual)
    }

    @Test
    fun transposeMap() {
        val expected = arrayOf(
            arrayOf(1, 2, 3),
            arrayOf(4, 5, 6),
            arrayOf(7, 8, 9)
        ).toMap()

        val actual = arrayOf(
            arrayOf(1, 4, 7),
            arrayOf(2, 5, 8),
            arrayOf(3, 6, 9)
        ).toMap().transpose()

        assertEquals(expected, actual)
    }

    @Test
    fun transposeNonSquareMap() {
        val expected = arrayOf(
            arrayOf(1, 2),
            arrayOf(4, 5),
            arrayOf(7, 8)
        ).toMap()

        val actual = arrayOf(
            arrayOf(1, 4, 7),
            arrayOf(2, 5, 8)
        ).toMap().transpose()

        assertEquals(expected, actual)
    }

    @Test
    fun transposeJaggedMap() {
        val expected = arrayOf(
            arrayOf(1, 2, 3),
            arrayOf(4, 5),
            arrayOf(7, 8)
        ).toMap()

        val actual = arrayOf(
            arrayOf(1, 4, 7),
            arrayOf(2, 5, 8),
            arrayOf(3)
        ).toMap().transpose()

        assertEquals(expected, actual)
    }

}