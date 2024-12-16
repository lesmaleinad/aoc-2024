package com.lesmaleinad.aoc2024

import com.lesmaleinad.aoc2024.utils.Direction
import com.lesmaleinad.aoc2024.utils.Location
import com.lesmaleinad.aoc2024.utils.Vector

private typealias Points = MutableMap<Vector, Location<Char>>

object Day15 {
    private const val WALL = '#'
    private const val BOX = 'O'
    private const val ROBOT = '@'
    private const val LEFT_BOX = '['
    private const val RIGHT_BOX = ']'

    private fun Char.toDir(): Direction? =
        when (this) {
            '^' -> Direction.UP
            'v' -> Direction.DOWN
            '<' -> Direction.LEFT
            '>' -> Direction.RIGHT
            else -> null
        }

    private fun String.toPoints(): Points {
        return lineSequence().withIndex().flatMap { (y, row) ->
            row.withIndex().mapNotNull { (x, char) ->
                if (char == '.') null else Location(Vector(x, y), char)
            }
        }.associateBy { it.vector }.toMutableMap()
    }

    private fun readInput(input: String): Pair<MutableMap<Vector, Location<Char>>, Sequence<Direction>> {
        val (gridStr, dirStr) = input.split("\n\n", "\r\n\r\n")
        return gridStr.toPoints() to dirStr.asSequence().mapNotNull { it.toDir() }
    }

    private fun Points.getGPSSum(): Int {
        return values.sumOf { (vector, char) -> if (char == BOX || char == LEFT_BOX) vector.y * 100 + vector.x else 0 }
    }

    private fun Points.findStart(): Location<Char> {
        return values.find { it.value == ROBOT } ?: throw IllegalArgumentException("No start found")
    }

    private fun Points.applyDirection(
        current: Location<Char>,
        dir: Direction,
    ): Location<Char> {
        val step = current.vector + dir
        val stepValue = get(step)
        return when (stepValue?.value) {
            null -> {
                remove(current.vector)
                Location(step, ROBOT).also { set(step, it) }
            }
            else -> {
                var next = step
                while (get(next)?.value == BOX) {
                    next += dir
                }
                when (get(next)?.value) {
                    null -> {
                        set(next, Location(next, BOX))
                        remove(current.vector)
                        Location(step, ROBOT).also { set(step, it) }
                    }
                    WALL -> current
                    else -> throw IllegalArgumentException("What the ${get(next)}")
                }
            }
        }
    }

    /**
     * The lanternfish already have a map of the warehouse and a list of movements the robot will attempt to make.
     * The movements will sometimes fail as boxes are shifted around, making the actual movements of the robot
     * difficult to predict.
     *
     * As the robot (@) attempts to move, if there are any boxes (O) in the way,
     * the robot will also attempt to push those boxes.
     * However, if this action would cause the robot or a box to move into a wall (#),
     * nothing moves instead, including the robot.
     *
     * The GPS coordinate of a box is equal to 100 times its distance from the top edge of the map plus
     * its distance from the left edge of the map.
     */
    fun part1(input: String): Int {
        val (points, dirs) = readInput(input)
        var current = points.findStart()
        for (dir in dirs) {
            current = points.applyDirection(current, dir)
        }
        return points.getGPSSum()
    }

    private fun Points.scaleUp(): Points {
        return values.flatMap { (vector, char) ->
            val first = vector + Vector(vector.x, 0)
            val second = first + Vector(1, 0)
            when (char) {
                WALL -> listOf(Location(first, WALL), Location(second, WALL))
                BOX -> listOf(Location(first, LEFT_BOX), Location(second, RIGHT_BOX))
                else -> listOf(Location(first, ROBOT))
            }
        }.associateBy { it.vector }.toMutableMap()
    }

    private fun Location<Char>.getOtherBox(): Location<Char> =
        when (value) {
            RIGHT_BOX -> Location(vector + Direction.LEFT, LEFT_BOX)
            LEFT_BOX -> Location(vector + Direction.RIGHT, RIGHT_BOX)
            else -> throw Exception("NOT A BOX. $this")
        }

    private fun Points.getBoxGroup(
        location: Location<Char>,
        dir: Direction,
    ): Set<Location<Char>>? {
        val nextVector = location.vector + dir
        val next = get(nextVector)
        return when (next?.value) {
            null -> setOf(location)
            WALL -> null
            else ->
                when (dir) {
                    Direction.LEFT, Direction.RIGHT -> getBoxGroup(next, dir).let { group -> group?.let { it + location } }
                    Direction.UP, Direction.DOWN ->
                        listOf(next, next.getOtherBox()).fold(setOf(location) as Set<Location<Char>>?) { acc, loc ->
                            acc?.let { getBoxGroup(loc, dir)?.let { it + acc } }
                        }
                }
        }
    }

    private fun Points.applyScaledDirection(
        current: Location<Char>,
        dir: Direction,
    ): Location<Char> {
        val step = current.vector + dir
        val stepValue = get(step)
        return when (stepValue?.value) {
            null -> {
                remove(current.vector)
                Location(step, ROBOT).also { set(step, it) }
            }
            WALL -> current
            else -> {
                val targets =
                    when (dir) {
                        Direction.LEFT, Direction.RIGHT -> listOf(stepValue)
                        Direction.UP, Direction.DOWN -> listOf(stepValue, stepValue.getOtherBox())
                    }
                val boxGroup = targets.map { getBoxGroup(it, dir) }.reduce { a, b -> a?.let { b?.let { a + b } } }
                if (boxGroup == null) {
                    current
                } else {
                    boxGroup.forEach {
                        remove(it.vector)
                    }
                    boxGroup.forEach {
                        val new = it + dir
                        set(new.vector, new)
                    }
                    remove(current.vector)
                    Location(step, ROBOT).also { set(step, it) }
                }
            }
        }
    }

    /**
     * Reports start coming in that a second warehouse's robot is also malfunctioning.
     * Everything except the robot is twice as wide!
     *
     * Because boxes are now twice as wide but the robot is still the same size and speed,
     * boxes can be aligned such that they directly push two other boxes at once.
     *
     * If the tile is #, the new map contains ## instead.
     * If the tile is O, the new map contains [] instead.
     * If the tile is ., the new map contains .. instead.
     * If the tile is @, the new map contains @. instead.
     */
    fun part2(input: String): Int {
        val (points, dirs) = readInput(input)
        val scaledPoints = points.scaleUp()
        var current = scaledPoints.findStart()
        for (dir in dirs) {
            current = scaledPoints.applyScaledDirection(current, dir)
        }
        return scaledPoints.getGPSSum()
    }
}
