import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

fun <T> List<List<T>>.toMap(): Map<Int, Map<Int, T>> {
    return withIndex().associate { (index, ints) ->
        index to ints.withIndex().associate { (index, value) -> index to value }
    }
}

fun <T> Map<Int, Map<Int, T>>.transpose(): Map<Int, Map<Int, T>> {
    val newMap = mutableMapOf<Int, MutableMap<Int, T>>()

    for (y in 0 until size) {
        for (x in 0 until this[y]!!.size) {
            if (!newMap.containsKey(x)) newMap[x] = mutableMapOf()
            newMap[x]!![y] = this[y]!![x]!!
        }
    }
    return newMap
}

fun clamp(value: Int, min: Int, max: Int): Int {
    return min(max, max(min, value))
}

fun clamp(value: Float, min: Float, max: Float): Float {
    return min(max, max(min, value))
}

fun randRange(min: Int, max: Int): Int {
    return min + (rand(0) * (max - min + 1))
}

fun randRange(seed: Long, min: Int, max: Int): Int {
    return min + (rand(seed) / 100.toDouble() * (max - min + 1)).toInt()
}

fun rand(seed: Long): Int {
    val rand = Random(100 + seed * 1000)
    return (rand.nextDouble() * 100).toInt()
}

/**
 * @return a float between -1 and 1
 */
fun getPercent(amount: Int, total: Int): Float {
    val center = (total / 2).toFloat()
    return (amount - center) / center
}
