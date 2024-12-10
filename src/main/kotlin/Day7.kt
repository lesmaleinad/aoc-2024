package com.lesmaleinad.aoc2024

object Day7 {
    data class Equation(val sum: Long, val numbers: List<Long>)

    private fun readInput(input: String): Sequence<Equation> {
        return input
            .lineSequence()
            .filter { it.isNotEmpty() }
            .map { line ->
                val parts = line.split(": ")
                val sum = parts[0].toLong()
                val numbers = parts[1].split(" ").map { it.toLong() }
                Equation(sum, numbers)
            }
    }

    /**
     * Each line represents a single equation. The test value appears before the colon on each line;
     * it is your job to determine whether the remaining numbers can be combined with operators to
     * produce the test value.
     *
     * Operators are always evaluated left-to-right, not according to precedence rules.
     * Furthermore, numbers in the equations cannot be rearranged.
     * Glancing into the jungle, you can see elephants holding two different types of operators:
     * add (+) and multiply (*).
     */

    private fun Equation.isValid(
        acc: Long = numbers.first(),
        depth: Int = 1,
        operators: List<(Long, Long) -> Long>,
    ): Boolean {
        val number = numbers.getOrNull(depth) ?: return acc == sum
        return operators.any { isValid(it(acc, number), depth + 1, operators) }
    }

    fun part1(input: String): Long {
        val equations = readInput(input)
        return equations.sumOf { equation ->
            if (equation.isValid(
                    operators =
                        listOf(
                            { a, b -> a + b },
                            { a, b -> a * b },
                        ),
                )
            ) {
                equation.sum
            } else {
                0
            }
        }
    }

    /**
     * Just then, you spot your mistake: some well-hidden elephants are holding a third type of operator.
     *
     * The concatenation operator (||) combines the digits from its left and right inputs into a single number.
     * For example, 12 || 345 would become 12345. All operators are still evaluated left-to-right.
     */
    fun part2(input: String): Long {
        val equations = readInput(input)
        return equations.sumOf { equation ->
            if (equation.isValid(
                    operators =
                        listOf(
                            { a, b -> a + b },
                            { a, b -> a * b },
                            { a, b -> "$a$b".toLong() },
                        ),
                )
            ) {
                equation.sum
            } else {
                0
            }
        }
    }
}
