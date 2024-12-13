package com.lesmaleinad.aoc2024

import com.lesmaleinad.aoc2024.utils.LongVector

object Day13 {
    private data class ClawMachine(val a: LongVector, val b: LongVector, val prize: LongVector)

    private fun String.getVector(): LongVector =
        Regex("X[+=](\\d+), Y[+=](\\d+)").find(this)!!.let {
            LongVector(it.groupValues[1].toLong(), it.groupValues[2].toLong())
        }

    private fun readInput(input: String): List<ClawMachine> {
        return input.split("\n\n", "\r\n\r\n").map { str ->
            val (a, b, prize) = str.lines().map { it.getVector() }
            ClawMachine(a, b, prize)
        }
    }

    private fun getCost(
        machine: ClawMachine,
        prizeOffset: Long = 0,
    ): Long {
        // iAX + jBX = PX
        // iAY + jBY = PY
        // i = (PX - jBX)/AX
        // (PX - jBX)*AY/AX + jBY = PY
        // PX*AY - jBX*AY + jBY*AX = PY*AX
        // j(BY*AX - BX*AY) = PY*AX - PX*AY
        // j = (PY*AX - PX*AY) / (BY*AX - BX*AY)

        val (a, b, prize) = machine
        val p = prize + LongVector(prizeOffset, prizeOffset)

        val bCount = (p.y * a.x - p.x * a.y) / (b.y * a.x - b.x * a.y)
        val aCount = (p.x - bCount * b.x) / a.x

        if (a * aCount + b * bCount != p) {
            return 0
        }

        return aCount * 3 + bCount
    }

    /**
     * These machines have two buttons labeled A and B. Worse, you can't just put in a token and play;
     * it costs 3 tokens to push the A button and 1 token to push the B button.
     *
     * Each machine's buttons are configured to move the claw a specific amount to the right (along the X axis)
     * and a specific amount forward (along the Y axis) each time that button is pressed.
     *
     * Each machine contains one prize; to win the prize, the claw must be positioned exactly above the prize on both the X and Y axes.
     *
     * Each button would need to be pressed no more than 100 times to win a prize.
     *
     * What is the fewest tokens you would have to spend to win all possible prizes?
     */
    fun part1(input: String): Long {
        val machines = readInput(input)
        return machines.sumOf { getCost(it) }
    }

    /**
     * The position of every prize is actually 10000000000000 higher on both the X and Y axis!
     */
    fun part2(input: String): Long {
        val machines = readInput(input)
        return machines.sumOf { getCost(it, 10000000000000L) }
    }
}
