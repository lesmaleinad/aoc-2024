package com.lesmaleinad.aoc2024

import com.lesmaleinad.aoc2024.utils.Grid
import com.lesmaleinad.aoc2024.utils.Location
import com.lesmaleinad.aoc2024.utils.Vector
import com.lesmaleinad.aoc2024.utils.getLocation
import com.lesmaleinad.aoc2024.utils.toGrid

object Day10 {
    private fun readInput(input: String): Grid<Int> {
        return input.toGrid { it.digitToInt() }
    }

    private val dirs =
        listOf(
            Vector(x = 1, y = 0),
            Vector(-1, 0),
            Vector(0, 1),
            Vector(0, -1),
        )

    private fun getTrailCount(
        grid: Grid<Int>,
        location: Location<Int>,
        reached: MutableSet<Vector>,
    ): Set<Vector> {
        val current = location.value
        if (current == 9) {
            reached.add(location.vector)
            return reached
        }

        dirs.forEach { dir ->
            val next = grid.getLocation(location.vector + dir)
            if (next?.value == current + 1) {
                getTrailCount(grid, next, reached)
            }
        }

        return reached
    }

    /**
     * A hiking trail is any path that starts at height 0, ends at height 9,
     * and always increases by a height of exactly 1 at each step.
     *
     * A trailhead is any position that starts one or more hiking trails - here,
     * these positions will always have height 0.
     *
     * A trailhead's score is the number of 9-height positions reachable from that trailhead via a hiking trail.
     */
    fun part1(input: String): Int {
        val grid = readInput(input)
        return grid.withIndex().sumOf { (y, row) ->
            row.withIndex().sumOf { (x, int) ->
                if (int == 0) {
                    getTrailCount(grid, Location(Vector(x, y), 0), mutableSetOf()).size
                } else {
                    0
                }
            }
        }
    }

    private fun getTrailRating(
        grid: Grid<Int>,
        location: Location<Int>,
    ): Int {
        val current = location.value
        if (current == 9) {
            return 1
        }

        return dirs.sumOf { dir ->
            val next = grid.getLocation(location.vector + dir)
            if (next?.value == current + 1) {
                getTrailRating(grid, next)
            } else {
                0
            }
        }
    }

    /**
     * The paper describes a second way to measure a trailhead called its rating.
     * A trailhead's rating is the number of distinct hiking trails which begin at that trailhead.
     */
    fun part2(input: String): Int {
        val grid = readInput(input)
        return grid.withIndex().sumOf { (y, row) ->
            row.withIndex().sumOf { (x, int) ->
                if (int == 0) {
                    getTrailRating(grid, Location(Vector(x, y), 0))
                } else {
                    0
                }
            }
        }
    }
}
