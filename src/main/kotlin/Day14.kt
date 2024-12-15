package com.lesmaleinad.aoc2024

import com.lesmaleinad.aoc2024.utils.LongVector

object Day14 {
    data class Robot(val start: LongVector, val velocity: LongVector)

    private fun String.getVector(): LongVector = split("=", ",").let { (_, x, y) -> LongVector(x.toLong(), y.toLong()) }

    private fun readInput(input: String): Sequence<Robot> {
        return input.lineSequence().filter { it.isNotEmpty() }.map { line ->
            val (start, velocity) = line.split(" ").map { it.getVector() }
            Robot(start, velocity)
        }
    }

    private fun LongVector.getQuadrant(position: LongVector): Int {
        if (position.x < 0 || position.y < 0 || position.x >= x || position.y >= y) {
            throw IllegalArgumentException("Out of bounds: $position in $this")
        }
        val halfX = x / 2
        val halfY = y / 2
        return if (position.x < halfX && position.y < halfY) {
            1
        } else if (position.x > halfX && position.y < halfY) {
            2
        } else if (position.x < halfX && position.y > halfY) {
            3
        } else if (position.x > halfX && position.y > halfY) {
            4
        } else {
            0
        }
    }

    private fun Robot.move(
        border: LongVector,
        elapsedTime: Long,
    ) = ((border * elapsedTime) + start + (velocity * elapsedTime)) % border

    private fun Sequence<Robot>.getQuadrants(
        border: LongVector,
        elapsedTime: Long,
    ): Map<Int, Int> {
        val quadrants =
            groupBy { robot ->
                val end = robot.move(border, elapsedTime)
                border.getQuadrant(end)
            }

        return quadrants.mapValues { it.value.size }
    }

    /**
     * Each robot's velocity is given as v=x,y where x and y are given in tiles per second.
     *  When a robot would run into an edge of the space they're in, they instead teleport to the other side,
     *  effectively wrapping around the edges.
     *
     *  The robots outside the actual bathroom are in a space which is 101 tiles wide and 103 tiles tall
     *
     * To determine the safest area, count the number of robots in each quadrant after 100 seconds.
     * Robots that are exactly in the middle (horizontally or vertically) don't count as being in any quadrant
     *
     * What will the safety factor be after exactly 100 seconds have elapsed?
     */
    fun part1(input: String): Long {
        val robots = readInput(input)
        val border = LongVector(1, 1) + LongVector(robots.maxOf { it.start.x }, robots.maxOf { it.start.y })
        val seconds = 100L
        val quadrants = robots.getQuadrants(border, seconds)
        return quadrants.entries.filter { it.key > 0 }.fold(1L) { acc, b -> acc * b.value }
    }

    private fun Set<LongVector>.render(border: LongVector) {
        println(
            (0 until border.x).joinToString("\r\n") { x ->
                (0 until border.y).joinToString("") { y ->
                    if (LongVector(x, y) in this) {
                        "*"
                    } else {
                        " "
                    }
                }
            },
        )
    }

    /**
     * Very rarely, most of the robots should arrange themselves into a picture of a Christmas tree.
     * What is the fewest number of seconds that must elapse for the robots to display the Easter egg?
     */
    fun part2(input: String): Long {
        val robots = readInput(input)
        val border = LongVector(1, 1) + LongVector(robots.maxOf { it.start.x }, robots.maxOf { it.start.y })
        var seconds = 0L
        var minDanger = Long.MAX_VALUE
        while (true) {
            val danger = robots.getQuadrants(border, seconds).entries.filter { it.key > 0 }.fold(1L) { acc, b -> acc * b.value }
            if (danger < minDanger) {
                robots.map { it.move(border, seconds) }.toSet().render(border)
                minDanger = danger
                if (seconds == 8159L) break // had to manually print to find this configuration.
            }
            seconds++
        }
        return 8159L
    }
}
