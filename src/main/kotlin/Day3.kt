package com.lesmaleinad.aoc2024

object Day3 {
    private val regex = """mul\((\d+),(\d+)\)""".toRegex()

    /**
     * It seems like the goal of the program is just to multiply some numbers. It does that with instructions like mul(X,Y),
     * where X and Y are each 1-3 digit numbers. For instance, mul(44,46) multiplies 44 by 46 to get a result of 2024.
     * Similarly, mul(123,4) would multiply 123 by 4.
     *
     * However, because the program's memory has been corrupted, there are also many invalid characters that should be ignored,
     * even if they look like part of a mul instruction. Sequences like mul(4*, mul(6,9!, ?(12,34), or mul ( 2 , 4 ) do nothing.
     */
    fun part1(input: String): Int {
        return regex.findAll(input).sumOf { it.groupValues[1].toInt() * it.groupValues[2].toInt() }
    }

    private val regex_part2 = """(don't\(\)|do\(\)|mul\(\d+,\d+\))""".toRegex()

    /**
     * There are two new instructions you'll need to handle:
     *
     * The do() instruction enables future mul instructions.
     * The don't() instruction disables future mul instructions.
     *
     * Only the most recent do() or don't() instruction applies. At the beginning of the program, mul instructions are enabled.
     */
    fun part2(input: String): Int {
        val commands = regex_part2.findAll(input).map { it.groupValues[1] }.toList()
        var doCommand = true
        return commands.sumOf { command ->
            when (command) {
                "do()" -> {
                    doCommand = true
                    0
                }
                "don't()" -> {
                    doCommand = false
                    0
                }
                else ->
                    if (doCommand) {
                        val groupValues = regex.find(command)!!.groupValues
                        groupValues[1].toInt() * groupValues[2].toInt()
                    } else {
                        0
                    }
            }
        }
    }
}
