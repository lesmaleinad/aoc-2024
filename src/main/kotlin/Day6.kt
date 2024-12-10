package com.lesmaleinad.aoc2024

import com.lesmaleinad.aoc2024.utils.Direction
import com.lesmaleinad.aoc2024.utils.Location
import com.lesmaleinad.aoc2024.utils.Vector
import com.lesmaleinad.aoc2024.utils.get
import com.lesmaleinad.aoc2024.utils.getLocation
import com.lesmaleinad.aoc2024.utils.set

object Day6 {
    private fun readInput(input: String): List<MutableList<Char>> {
        return input.lineSequence().filter { it.isNotEmpty() }.map { it.toMutableList() }.toList()
    }

    private fun findStart(grid: List<List<Char>>): Location<Char> {
        for ((y, row) in grid.withIndex()) {
            for ((x, char) in row.withIndex()) {
                if (char != '.' && char != '#') {
                    return Location(Vector(x, y), char)
                }
            }
        }

        throw IllegalArgumentException("No start found")
    }

    private fun Char.toDir(): Direction =
        when (this) {
            '^' -> Direction.UP
            'v' -> Direction.DOWN
            '<' -> Direction.LEFT
            '>' -> Direction.RIGHT
            else -> throw IllegalArgumentException("Invalid direction: $this")
        }

    private fun Direction.toChar(): Char =
        when (this) {
            Direction.UP -> '^'
            Direction.DOWN -> 'v'
            Direction.LEFT -> '<'
            Direction.RIGHT -> '>'
        }

    private tailrec fun findSecurity(
        grid: List<MutableList<Char>>,
        visited: MutableSet<Vector>,
    ): Set<Vector> {
        val current = findStart(grid)

        visited.add(current.vector)

        var nextDir = current.value.toDir()
        var nextLocation = grid.getLocation(current.vector + nextDir) ?: return visited

        if (nextLocation.value == '#') {
            nextDir = nextDir.toRight()
            nextLocation = grid.getLocation(current.vector + nextDir) ?: return visited
        }

        if (nextLocation.value == '#') {
            return visited
        }

        grid[nextLocation.vector] = nextDir.toChar()
        grid[current.vector] = '.'
        return findSecurity(grid, visited)
    }

    /**
     * Lab guards in 1518 follow a very strict patrol protocol
     * which involves repeatedly following these steps:
     *
     * If there is something directly in front of you, turn right 90 degrees.
     * Otherwise, take a step forward.
     */
    fun part1(input: String): Int {
        val grid = readInput(input)
        val visited = findSecurity(grid, mutableSetOf())
        return visited.size
    }

    private tailrec fun detectLoop(
        currentLocation: Location<Char>,
        grid: List<List<Char>>,
        visited: MutableSet<Location<Char>>,
    ): Boolean {
        if (visited.contains(currentLocation)) {
            return true
        }

        visited.add(currentLocation)

        val dir = currentLocation.value.toDir()
        val nextVec = currentLocation.vector + dir
        val nextChar = grid[nextVec]
        return when (nextChar) {
            null -> false
            '#' -> detectLoop(currentLocation.copy(value = dir.toRight().toChar()), grid, visited)
            else -> detectLoop(currentLocation.copy(vector = nextVec), grid, visited)
        }
    }

    private tailrec fun findLoops(
        current: Location<Char>,
        grid: List<MutableList<Char>>,
        visited: MutableSet<Vector>,
        vectors: MutableSet<Vector>,
    ): Set<Vector> {
        val direction = current.value.toDir()
        val nextLocation = grid.getLocation(current.vector + direction)
        visited.add(current.vector)

        return when (nextLocation?.value) {
            null -> vectors
            '#' -> findLoops(current.copy(value = direction.toRight().toChar()), grid, visited, vectors)
            else -> {
                if (nextLocation.vector !in visited &&
                    detectLoop(
                        currentLocation = current,
                        grid =
                            grid.map { it.toMutableList() }.apply {
                                this[nextLocation.vector] = '#'
                            },
                        visited = mutableSetOf(),
                    )
                ) {
                    vectors.add(nextLocation.vector)
                }
                findLoops(current.copy(vector = nextLocation.vector), grid, visited, vectors)
            }
        }
    }

    /**
     *
     */
    fun part2(input: String): Int {
        val grid = readInput(input)
        val start = findStart(grid)
        return findLoops(start, grid, mutableSetOf(), mutableSetOf()).size
    }
}
