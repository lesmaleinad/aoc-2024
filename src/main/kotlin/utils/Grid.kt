package com.lesmaleinad.aoc2024.utils

typealias Grid<T> = List<List<T>>
typealias MutableGrid<T> = List<MutableList<T>>

fun String.toGrid() = toGrid { it }

fun <T> String.toGrid(transform: (Char) -> T) = lineSequence().filter { it.isNotEmpty() }.map { it.map(transform) }.toList()

fun Grid<*>.contains(vector: Vector): Boolean = getOrNull(vector.y)?.indices?.contains(vector.x) == true

operator fun <T> Grid<T>.get(vector: Vector): T? {
    return getOrNull(vector.y)?.getOrNull(vector.x)
}

fun <T> Grid<T>.getValue(vector: Vector): T = get(vector.y)[vector.x]

operator fun <T> MutableGrid<T>.set(
    vector: Vector,
    value: T,
): T? = getOrNull(vector.y)?.set(vector.x, value)
