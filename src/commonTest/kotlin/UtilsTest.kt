import kotlin.test.Test
import kotlin.test.assertEquals

class UtilsTest {

    @Test
    fun arraysToMap() {
        val expected = mapOf(
            0 to mapOf(0 to 1, 1 to 2, 2 to 3),
            1 to mapOf(0 to 4, 1 to 5, 2 to 6),
            2 to mapOf(0 to 7, 1 to 8, 2 to 9)
        )

        val actual = listOf(
            listOf(1, 2, 3),
            listOf(4, 5, 6),
            listOf(7, 8, 9)
        ).toMap()

        assertEquals(expected, actual)
    }

    @Test
    fun transposeMap() {
        val expected = listOf(
            listOf(1, 2, 3),
            listOf(4, 5, 6),
            listOf(7, 8, 9)
        ).toMap()

        val actual = listOf(
            listOf(1, 4, 7),
            listOf(2, 5, 8),
            listOf(3, 6, 9)
        ).toMap().transpose()

        assertEquals(expected, actual)
    }

    @Test
    fun transposeNonSquareMap() {
        val expected = listOf(
            listOf(1, 2),
            listOf(4, 5),
            listOf(7, 8)
        ).toMap()

        val actual = listOf(
            listOf(1, 4, 7),
            listOf(2, 5, 8)
        ).toMap().transpose()

        assertEquals(expected, actual)
    }

    @Test
    fun transposeJaggedMap() {
        val expected = listOf(
            listOf(1, 2, 3),
            listOf(4, 5),
            listOf(7, 8)
        ).toMap()

        val actual = listOf(
            listOf(1, 4, 7),
            listOf(2, 5, 8),
            listOf(3)
        ).toMap().transpose()

        assertEquals(expected, actual)
    }

    @Test
    fun clap() {
        assertEquals(0, clamp(-10, 0, 5))
        assertEquals(5, clamp(10, 0, 5))
        assertEquals(10, clamp(10, 0, 100))
    }

}
