package com.lesmaleinad.aoc2024

object Day2 {
    data class Level(val values: List<Int>)

    private fun parse(input: String): List<Level> {
        return input.lineSequence()
            .filter { it.isNotEmpty() }
            .map { it.split(" ").map(String::toInt) }
            .map { Level(it) }
            .toList()
    }

    private fun safeValues(values: List<Int>): Boolean {
        val inc = values[0] < values[1]
        return values.windowed(2).all { (a, b) -> b - a in if (inc) 1..3 else -3..-1 }
    }

    /**
     *  The Red-Nosed reactor safety systems can only tolerate levels that are either gradually increasing or gradually decreasing.
     *  So, a report only counts as safe if both of the following are true:
     *
     * The levels are either all increasing or all decreasing.
     * Any two adjacent levels differ by at least one and at most three.
     */
    fun part1(input: String): Int {
        val levels = parse(input)
        return levels.count { level ->
            safeValues(level.values)
        }
    }

    private fun <T> List<T>.without(index: Int): List<T> {
        return this.filterIndexed { i, _ -> i != index }
    }

    /**
     * The Problem Dampener is a reactor-mounted module that lets the reactor safety systems tolerate a single bad level
     * in what would otherwise be a safe report. It's like the bad level never happened!
     *
     * Now, the same rules apply as before, except if removing a single level from an unsafe report would make it safe,
     * the report instead counts as safe.
     */
    fun part2(input: String): Int {
        val levels = parse(input)
        return levels.count { level ->
            level.values.indices.any { safeValues(level.values.without(it)) }
        }
    }
}
