package com.lesmaleinad.aoc2024

import kotlin.math.absoluteValue

object Day1 {
  
  private fun readInput(input: String): Pair<List<Int>, List<Int>> {
    val (list1, list2) = input.lineSequence()
      .filter { it.isNotEmpty() }
      .map { "(\\d+)\\s+(\\d+)".toRegex().matchEntire(it)!!.groupValues }
      .map { it[1].toInt() to it[2].toInt() }
      .unzip()
    return list1 to list2
  }
  
  /**
   * Maybe the lists are only off by a small amount!
   * To find out, pair up the numbers and measure how far apart they are.
   * Pair up the smallest number in the left list with the smallest number in the right list,
   * then the second-smallest left number with the second-smallest right number, and so on.
   * Within each pair, figure out how far apart the two numbers are; you'll need to add up all of those distances.
   */
  fun part1(input: String): Int {
    val (list1, list2) = readInput(input)
    return list1.sorted().zip(list2.sorted()).sumOf { (a, b) -> (a - b).absoluteValue }
  }
  
  /**
   * This time, you'll need to figure out exactly how often each number from the left list appears in the right list.
   * Calculate a total similarity score by adding up each number in the left list after multiplying it by the number of times
   * that number appears in the right list.
   */
  fun part2(input: String): Int {
    val (list1, list2) = readInput(input)
    val counts = list2.groupingBy { it }.eachCount()
    return list1.sumOf { it * counts.getOrDefault(it, 0) }
  }
}
