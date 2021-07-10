package planet.generation

import kotlin.math.max
import kotlin.math.min

class MapTiler(private val percent: Float = .1f) {

    fun makeImageTilable(ogMap: Array<IntArray>): Array<IntArray> {
        val size = ogMap.size
        val overlapAmount = (size * percent).toInt()
        if (overlapAmount >= size || overlapAmount == 0) {
            return ogMap
        }

        val map = ogMap.copyOf()
        val verticalOverlap = getVerticalOverlap(map, overlapAmount)
        blend(map, verticalOverlap)
        val horizontalOverlap = getHorizontalOverlap(map, overlapAmount)
        blend(map, horizontalOverlap)
        return map
    }

    private fun getVerticalOverlap(map: Array<IntArray>, overlapAmount: Int): Array<IntArray> {
        val size = map.size
        val verticalOverlap = Array(overlapAmount) { IntArray(size) }
        val start = size - overlapAmount
        var x = start
        var i = 1
        while (x < map.size) {
            val column = map[x].clone()
            //invert the overlap
            verticalOverlap[overlapAmount - i] = column
            x++
            i++
        }
        return verticalOverlap
    }

    private fun getHorizontalOverlap(map: Array<IntArray>, overlapAmount: Int): Array<IntArray> {
        val size = map.size
        val horizontalOverlap = Array(size) { IntArray(overlapAmount) }
        val start = size - overlapAmount
        var y = start
        var i = 1
        while (y < map.size) {
            for (x in map.indices) {
                //invert the overlap
                horizontalOverlap[x][overlapAmount - i] = map[x][y]
            }
            y++
            i++
        }
        return horizontalOverlap
    }

    private fun blend(map: Array<IntArray>, overlap: Array<IntArray>) {
        val xIsPrimary = xAxisIsShorterThanYAxis(overlap)
        val overlapAmount = getShorterLength(overlap)
        val fullAmount = getLongerLength(overlap)
        for (primary in 0 until overlapAmount) {
            val originalPercent = (primary + 1) / overlapAmount.toFloat()
            val overlapPercent = 1 - originalPercent
            if (originalPercent == 1f) {
                continue
            }
            for (secondary in 0 until fullAmount) {
                val x = if (xIsPrimary) primary else secondary
                val y = if (xIsPrimary) secondary else primary
                val original = map[x][y]
                val overlapped = overlap[x][y]
                val blended = (original * originalPercent + overlapped * overlapPercent).toInt()
                map[x][y] = blended
            }
        }
    }

    private fun getShorterLength(overlap: Array<IntArray>): Int {
        return min(overlap.size, overlap[0].size)
    }

    private fun getLongerLength(overlap: Array<IntArray>): Int {
        return max(overlap.size, overlap[0].size)
    }

    private fun xAxisIsShorterThanYAxis(overlap: Array<IntArray>): Boolean {
        return overlap.size < overlap[0].size
    }
}
