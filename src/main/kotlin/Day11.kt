package com.lesmaleinad.aoc2024

object Day11 {
    private fun readInput(input: String): List<Long> {
        return input.split(" ").map { it.toLong() }
    }

    private fun <A, T> List<(A) -> T>.getFirstNotNull(input: A): T {
        for (fn in this) {
            return fn(input) ?: continue
        }

        throw Exception("No value found!")
    }

    private fun String.inHalf(): List<Long> =
        listOf(
            slice(0..lastIndex / 2).toLong(),
            slice(lastIndex / 2 + 1..lastIndex).toLong(),
        )

    private val rules: List<(Long) -> List<Long>?> =
        listOf(
            { if (it == 0L) listOf(1) else null },
            { "$it".let { str -> if (str.length % 2 == 0) str.inHalf() else null } },
            { listOf(it * 2024) },
        )

    private val stoneCache: MutableMap<String, Long> = mutableMapOf()

    private fun countStones(
        stones: List<Long>,
        depth: Int = 0,
        max: Int = 25,
    ): Long {
        if (depth == max) return stones.size.toLong()
        return stones.sumOf { stone ->
            stoneCache.getOrPut("$stone $depth $max") { countStones(rules.getFirstNotNull(stone)!!, depth + 1, max) }
        }
    }

    /**
     * Every time you blink, the stones each simultaneously change according to the first applicable rule in this list:
     *
     * If the stone is engraved with the number 0, it is replaced by a stone engraved with the number 1.
     * If the stone is engraved with a number that has an even number of digits, it is replaced by two stones. The left half of the digits are engraved on the new left stone, and the right half of the digits are engraved on the new right stone. (The new numbers don't keep extra leading zeroes: 1000 would become stones 10 and 0.)
     * If none of the other rules apply, the stone is replaced by a new stone; the old stone's number multiplied by 2024 is engraved on the new stone.
     */
    fun part1(input: String): Long {
        val stones = readInput(input)
        return countStones(stones)
    }

    /**
     * How many stones would you have after blinking a total of 75 times?
     */
    fun part2(input: String): Long {
        val stones = readInput(input)
        return countStones(stones, max = 75)
    }
}
