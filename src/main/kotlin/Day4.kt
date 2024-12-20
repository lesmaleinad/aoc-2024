package com.lesmaleinad.aoc2024

import com.lesmaleinad.aoc2024.utils.Vector
import com.lesmaleinad.aoc2024.utils.get

object Day4 {
    private fun readInput(input: String): List<List<Char>> {
        return input.lineSequence().filter { it.isNotEmpty() }.map { it.toList() }.toList()
    }

    private val dirs =
        listOf(
            Vector(1, 0),
            Vector(0, 1),
            Vector(1, 1),
            Vector(-1, 1),
            Vector(1, -1),
            Vector(-1, -1),
            Vector(0, -1),
            Vector(-1, 0),
        )

    private val XMAS = "XMAS".asSequence()

    /**
     * This word search allows words to be horizontal, vertical, diagonal,
     * written backwards, or even overlapping other words.
     * It's a little unusual, though, as you don't merely need to find one instance of XMAS,
     * you need to find all of them.
     */
    fun part1(input: String): Int {
        val grid = readInput(input)
        return grid.withIndex().sumOf { (y, row) ->
            row.indices.sumOf { x ->
                val current = Vector(x, y)
                dirs.count { dir ->
                    XMAS.withIndex().all { (i, char) -> char == grid[current + dir * i] }
                }
            }
        }
    }

    private val diagonals =
        listOf(
            Vector(1, 1),
            Vector(-1, 1),
        )

    private fun isMS(
        a: Char?,
        b: Char?,
    ): Boolean = (a == 'M' && b == 'S') || (a == 'S' && b == 'M')

    /**
     * You're supposed to find two MAS in the shape of an X. One way to achieve that is like this:
     *
     * M.S
     * .A.
     * M.S
     *
     * Irrelevant characters have again been replaced with . in the above diagram.
     * Within the X, each MAS can be written forwards or backwards.
     */
    fun part2(input: String): Int {
        val grid = readInput(input)
        return grid.withIndex().sumOf { (y, row) ->
            row.indices.count { x ->
                val current = Vector(x, y)
                grid[current] == 'A' &&
                    diagonals.all { vector ->
                        isMS(grid[current + vector], grid[current - vector])
                    }
            }
        }
    }
}
