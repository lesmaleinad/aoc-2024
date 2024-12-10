package com.lesmaleinad.aoc2024

import com.lesmaleinad.aoc2024.utils.Location
import com.lesmaleinad.aoc2024.utils.Vector
import com.lesmaleinad.aoc2024.utils.contains

object Day8 {
    private fun readInput(input: String): List<List<Char>> {
        return input.lineSequence().filter { it.isNotEmpty() }.map { it.toList() }.toList()
    }

    private fun getNodes(grid: List<List<Char>>) =
        grid
            .asSequence()
            .withIndex()
            .map { (y, row) -> row.withIndex().map { (x, char) -> Location(Vector(x, y), char) } }
            .flatten()
            .filter { it.value != '.' }
            .groupBy { it.value }

    /**
     * Each antenna is tuned to a specific frequency indicated by a single lowercase letter, uppercase letter, or digit.
     * An antinode occurs at any point that is perfectly in line with two antennas of the same frequency - but only when
     * one of the antennas is twice as far away as the other.
     * This means that for any pair of antennas with the same frequency, there are two antinodes, one on either side of them.
     */
    fun part1(input: String): Int {
        val grid = readInput(input)
        val nodeMap = getNodes(grid)
        return nodeMap.values.fold(mutableSetOf<Vector>()) { set, nodes ->
            nodes.withIndex().forEach { (i, first) ->
                nodes.asSequence().drop(i + 1).forEach { second ->
                    // a - b = c
                    val diff = first.vector - second.vector

                    // a + c
                    val forwards = first.vector + diff

                    // b - c
                    val backwards = second.vector - diff

                    set.add(backwards)
                    set.add(forwards)
                }
            }
            set
        }.filter { grid.contains(it) }.size
    }

    private tailrec fun traverseVectors(
        current: Vector,
        vector: Vector,
        visited: MutableSet<Vector>,
    ) {
        visited.add(current)
        val next = current + vector
        return traverseVectors(next, vector, visited)
    }

    /**
     * It turns out that an antinode occurs at any grid position exactly in line with at least two antennas of the same frequency, regardless of distance.
     */
    fun part2(input: String): Int {
        val grid = readInput(input)
        val nodeMap = getNodes(grid)
        return nodeMap.values.fold(mutableSetOf<Vector>()) { set, nodes ->
            nodes.withIndex().forEach { (i, first) ->
                nodes.asSequence().drop(i + 1).forEach { second ->
                    // a - b = c
                    val diff = first.vector - second.vector

                    // a + c
                    var current = first.vector
                    while (grid.contains(current)) {
                        set.add(current)
                        current += diff
                    }

                    // b - c
                    current = second.vector
                    while (grid.contains(current)) {
                        set.add(current)
                        current -= diff
                    }
                }
            }
            set
        }.size
    }
}
