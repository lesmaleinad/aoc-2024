package com.lesmaleinad.aoc2024

object Day4 {
    private data class Vector(val x: Int, val y: Int) {
        operator fun plus(other: Vector) = Vector(x + other.x, y + other.y)

        operator fun minus(other: Vector) = Vector(x - other.x, y - other.y)

        operator fun times(scalar: Int) = Vector(x * scalar, y * scalar)
    }

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

    private operator fun <T> List<List<T>>.get(vector: Vector): T? {
        return getOrNull(vector.y)?.getOrNull(vector.x)
    }

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
                if (grid[current] == 'X') {
                    dirs.count { dir ->
                        XMAS.withIndex().drop(1).all { (i, char) -> char == grid[current + dir * i] }
                    }
                } else {
                    0
                }
            }
        }
    }

    private val diagonals =
        listOf(
            Vector(1, 1),
            Vector(-1, 1),
        )

    private fun oppositeMS(char: Char?): Char? =
        when (char) {
            'M' -> 'S'
            'S' -> 'M'
            else -> '_'
        }

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
                        grid[current + vector] == oppositeMS(grid[current - vector])
                    }
            }
        }
    }
}
