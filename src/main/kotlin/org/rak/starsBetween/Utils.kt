package org.rak.starsBetween

import kotlin.math.max
import kotlin.math.min

fun <T> List<List<T>>.toMap(): Map<Int, Map<Int, T>> {
    return withIndex().associate { (index, ints) ->
        index to ints.withIndex().associate { (index, value) -> index to value }
    }
}

fun <T> Map<Int, Map<Int, T>>.transpose(): Map<Int, Map<Int, T>> {
    val newMap = mutableMapOf<Int, MutableMap<Int, T>>()

    for (y in 0 until size) {
        for (x in 0 until this[y]!!.size) {
            newMap.putIfAbsent(x, mutableMapOf())
            newMap[x]!![y] = this[y]!![x]!!
        }
    }
    return newMap
}

fun clamp(value: Int, min: Int, max: Int): Int {
    return min(max, max(min, value))
}