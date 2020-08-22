import org.junit.Assert.assertEquals
import org.junit.Test

class AdjacencyTest {

    @Test
    fun oneSideOrientation() {
       val up = false
       val down = true
       val left = false
       val right = false

        assertEquals(0, Adjacency.ONE_SIDE.getRotation(up, down, left, right))
    }

    @Test
    fun oneSideOrientationRotated90() {
        val up = false
        val down = false
        val left = true
        val right = false

        assertEquals(90, Adjacency.ONE_SIDE.getRotation(up, down, left, right))
    }

    @Test
    fun oneSideOrientationRotated180() {
        val up = true
        val down = false
        val left = false
        val right = false

        assertEquals(180, Adjacency.ONE_SIDE.getRotation(up, down, left, right))
    }

    @Test
    fun oneSideOrientationRotated270() {
        val up = false
        val down = false
        val left = false
        val right = true

        assertEquals(270, Adjacency.ONE_SIDE.getRotation(up, down, left, right))
    }

    @Test
    fun twoSideOrientation() {
        val up = false
        val down = false
        val left = true
        val right = true

        assertEquals(0, Adjacency.TWO_SIDE.getRotation(up, down, left, right))
    }

    @Test
    fun twoSideOrientationRotated90() {
        val up = true
        val down = true
        val left = false
        val right = false

        assertEquals(90, Adjacency.TWO_SIDE.getRotation(up, down, left, right))
    }

    @Test
    fun cornerOrientation() {
        val up = false
        val down = true
        val left = true
        val right = false

        assertEquals(0, Adjacency.CORNER.getRotation(up, down, left, right))
    }

    @Test
    fun cornerOrientationRotated90() {
        val up = true
        val down = false
        val left = true
        val right = false

        assertEquals(90, Adjacency.CORNER.getRotation(up, down, left, right))
    }

    @Test
    fun cornerOrientationRotated180() {
        val up = true
        val down = false
        val left = false
        val right = true

        assertEquals(180, Adjacency.CORNER.getRotation(up, down, left, right))
    }

    @Test
    fun cornerOrientationRotated270() {
        val up = false
        val down = true
        val left = false
        val right = true

        assertEquals(270, Adjacency.CORNER.getRotation(up, down, left, right))
    }

    @Test
    fun threeSideOrientation() {
        val up = false
        val down = true
        val left = true
        val right = true

        assertEquals(0, Adjacency.THREE_SIDE.getRotation(up, down, left, right))
    }

    @Test
    fun threeSideOrientationRotated90() {
        val up = true
        val down = true
        val left = true
        val right = false

        assertEquals(90, Adjacency.THREE_SIDE.getRotation(up, down, left, right))
    }

    @Test
    fun threeSideOrientationRotated180() {
        val up = true
        val down = false
        val left = true
        val right = true

        assertEquals(180, Adjacency.THREE_SIDE.getRotation(up, down, left, right))
    }

    @Test
    fun threeSideOrientationRotated270() {
        val up = true
        val down = true
        val left = false
        val right = true

        assertEquals(270, Adjacency.THREE_SIDE.getRotation(up, down, left, right))
    }

}