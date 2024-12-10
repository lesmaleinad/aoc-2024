package com.lesmaleinad.aoc2024.utils

data class Location<V>(val vector: Vector, val value: V) {
    operator fun plus(other: Vector) = Location(vector + other, value)

    operator fun plus(dir: Direction) = Location(vector + dir.vector, value)
}

fun <T> List<List<T>>.getLocation(vector: Vector): Location<T>? = getOrNull(vector.y)?.getOrNull(vector.x)?.let { Location(vector, it) }
