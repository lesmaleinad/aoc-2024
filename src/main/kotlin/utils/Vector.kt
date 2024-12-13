package com.lesmaleinad.aoc2024.utils

data class Vector(val x: Int, val y: Int) {
    operator fun plus(other: Vector) = Vector(x + other.x, y + other.y)

    operator fun plus(dir: Direction) = Vector(x + dir.vector.x, y + dir.vector.y)

    operator fun minus(other: Vector) = Vector(x - other.x, y - other.y)

    operator fun times(scalar: Int) = Vector(x * scalar, y * scalar)
}

data class LongVector(val x: Long, val y: Long) {
    operator fun plus(other: LongVector) = LongVector(x + other.x, y + other.y)

    operator fun minus(other: LongVector) = LongVector(x - other.x, y - other.y)

    operator fun times(scalar: Long) = LongVector(x * scalar, y * scalar)

    operator fun times(other: LongVector) = LongVector(x * other.x, y * other.y)

    operator fun div(other: LongVector) = (other.x / other.y) / (x / y)
}

enum class Direction(val vector: Vector) {
    UP(Vector(0, -1)),
    DOWN(Vector(0, 1)),
    LEFT(Vector(-1, 0)),
    RIGHT(Vector(1, 0)),
    ;

    fun toLeft(): Direction {
        return when (this) {
            UP -> LEFT
            LEFT -> DOWN
            DOWN -> RIGHT
            RIGHT -> UP
        }
    }

    fun toRight(): Direction {
        return when (this) {
            UP -> RIGHT
            RIGHT -> DOWN
            DOWN -> LEFT
            LEFT -> UP
        }
    }
}
