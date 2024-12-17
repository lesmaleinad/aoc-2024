package com.lesmaleinad.aoc2024

import com.lesmaleinad.aoc2024.utils.Direction
import com.lesmaleinad.aoc2024.utils.Grid
import com.lesmaleinad.aoc2024.utils.Location
import com.lesmaleinad.aoc2024.utils.Vector
import com.lesmaleinad.aoc2024.utils.get
import com.lesmaleinad.aoc2024.utils.getValue
import com.lesmaleinad.aoc2024.utils.toGrid
import java.util.PriorityQueue

object Day16 {
    private const val START = 'E'
    private const val END = 'S'
    private const val WALL = '#'

    private fun readInput(input: String): Grid<Char> {
        return input.toGrid()
    }

    private data class Node(val location: Location<Direction>, val score: Int, private val previous: Node?) {
        constructor(x: Int, y: Int, dir: Direction, score: Int, previous: Node? = null) : this(Location(Vector(x, y), dir), score, previous)

        fun stepForward() = Node(location.copy(vector = location.vector + location.value), score + 1, this)

        fun toLeft() =
            Node(
                location.copy(vector = location.vector + location.value.toLeft(), value = location.value.toLeft()),
                score + 1001,
                this,
            )

        fun toRight() =
            Node(
                location.copy(vector = location.vector + location.value.toRight(), value = location.value.toRight()),
                score + 1001,
                this,
            )

        fun getNextNodes() = listOf(stepForward(), toLeft(), toRight())

        fun getPath(): Sequence<Node> =
            sequence {
                var next = previous
                while (next != null) {
                    yield(next)
                    next = next.previous
                }
            }
    }

    private fun Grid<Char>.getStart(): Node {
        for ((y, row) in withIndex()) {
            for ((x, char) in row.withIndex()) {
                if (char == START) {
                    return Node(x, y, Direction.RIGHT, 0)
                }
            }
        }

        throw Exception("START NOT FOUND")
    }

    private tailrec fun Grid<Char>.findPath(
        q: PriorityQueue<Node>,
        visited: MutableSet<Location<Direction>>,
        endNodes: MutableList<Node> = mutableListOf(),
    ): Node {
        val next = q.poll()

        if (getValue(next.location.vector) == END) {
            return next
        }

        visited.add(next.location)
        q.addAll(next.getNextNodes().filter { get(it.location.vector) != WALL && it.location !in visited })
        return findPath(q, visited, endNodes)
    }

    /**
     * The Reindeer start on the Start Tile (marked S) facing East and need to reach the End Tile (marked E).
     * They can move forward one tile at a time (increasing their score by 1 point), but never into a wall (#).
     * They can also rotate clockwise or counterclockwise 90 degrees at a time (increasing their score by 1000 points).
     *
     * What is the lowest score a Reindeer could possibly get?
     */
    fun part1(input: String): Int {
        val grid = readInput(input)
        val start = grid.getStart()
        val queue = PriorityQueue<Node> { nodeA, nodeB -> nodeA.score - nodeB.score }
        queue.add(start)
        return grid.findPath(queue, mutableSetOf()).score
    }

    private tailrec fun Grid<Char>.traversePaths(
        q: PriorityQueue<Node>,
        bestPaths: MutableMap<Location<Direction>, Int>,
        allPaths: MutableMap<Location<Direction>, Int>,
        maxScore: Int,
    ): Set<Vector> {
        val next = q.poll()

        if (next.score > maxScore) return bestPaths.keys.map { it.vector }.toSet()

        allPaths[next.location] = next.score
        if (bestPaths[next.location] == next.score || getValue(next.location.vector) == END) {
            next.getPath().forEach { node ->
                bestPaths[node.location] = node.score
            }
        }

        q.addAll(
            next.getNextNodes().filter {
                get(it.location.vector) != WALL &&
                    allPaths[it.location]?.let { bestScore -> bestScore == it.score } != false
            },
        )

        return traversePaths(q, bestPaths, allPaths, maxScore)
    }

    /**
     * Every non-wall tile (S, ., or E) is equipped with places to sit along the edges of the tile.
     * The most important factor is whether the tile is on one of the best paths through the maze.
     *
     * How many tiles are part of at least one of the best paths through the maze?s
     */
    fun part2(input: String): Int {
        val grid = readInput(input)
        val start = grid.getStart()
        val queue = PriorityQueue<Node> { nodeA, nodeB -> nodeA.score - nodeB.score }
        queue.add(start)
        val bestNode = grid.findPath(queue, mutableSetOf())
        val bestPaths = bestNode.getPath().associate { it.location to it.score }.toMutableMap()
        bestPaths[bestNode.location] = bestNode.score
        val allPaths = bestPaths.toMutableMap()
        queue.add(start)
        return grid.traversePaths(queue, bestPaths, allPaths, bestNode.score).size
    }
}
