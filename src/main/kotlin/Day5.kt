package com.lesmaleinad.aoc2024

object Day5 {
    private fun readInput(input: String): Pair<Map<String, List<String>>, Sequence<List<String>>> {
        val (rulesStr, updatesStr) = input.split("\n\n", "\r\n\r\n")
        val rules =
            rulesStr.lineSequence()
                .map { it.split("|") }
                .map { (before, after) -> after to before }
                .groupBy({ it.first }) { it.second }
        val updates = updatesStr.lineSequence().filter { it.isNotEmpty() }.map { it.split(",") }
        return rules to updates
    }

    private fun isValidOrder(
        rules: Map<String, List<String>>,
        update: List<String>,
    ): Boolean {
        val seen = mutableSetOf<String>()
        return update.all { page ->
            val ruleList = rules[page].orEmpty()
            if (ruleList.any { it in update && it !in seen }) {
                false
            } else {
                seen.add(page)
                true
            }
        }
    }

    private fun <T> List<T>.mid(): T = this[(size - 1) / 2]

    /**
     * The notation X|Y means that if both page number X and page number Y are to be produced as part of an update,
     * page number X must be printed at some point before page number Y.
     */
    fun part1(input: String): Int {
        val (rules, updates) = readInput(input)
        return updates.sumOf { update ->
            if (update.all { isValidOrder(rules, update) }) {
                update.mid().toInt()
            } else {
                0
            }
        }
    }

    /**
     * For each of the incorrectly-ordered updates, use the page ordering rules to put the page numbers in the right order.
     */
    fun part2(input: String): Int {
        val (rules, updates) = readInput(input)
        return updates.sumOf { update ->
            if (isValidOrder(rules, update)) {
                0
            } else {
                update.sortedWith { a, b ->
                    if (rules[b].orEmpty().any { it == a }) {
                        -1
                    } else if (rules[a].orEmpty().any { it == b }) {
                        1
                    } else {
                        0
                    }
                }.mid().toInt()
            }
        }
    }
}
