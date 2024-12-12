package com.lesmaleinad.aoc2024

import com.lesmaleinad.aoc2024.utils.Direction
import com.lesmaleinad.aoc2024.utils.Grid
import com.lesmaleinad.aoc2024.utils.Location
import com.lesmaleinad.aoc2024.utils.Vector
import com.lesmaleinad.aoc2024.utils.getLocation
import com.lesmaleinad.aoc2024.utils.toGrid

object Day12 {
    data class Plot(val area: MutableSet<Vector>, var perimeter: Int)

    private val dirs = Direction.entries

    private fun readInput(input: String): Grid<Char> {
        return input.toGrid()
    }

    private fun traversePlot(
        grid: Grid<Char>,
        current: Location<Char>,
        plot: Plot,
    ): Plot {
        plot.area.add(current.vector)
        dirs.forEach { dir ->
            val next = grid.getLocation(current.vector + dir)
            if (next?.value == current.value) {
                if (next.vector !in plot.area) {
                    traversePlot(grid, next, plot)
                }
            } else {
                plot.perimeter++
            }
        }
        return plot
    }

    /**
     * The area of a region is simply the number of garden plots the region contains.
     * Each garden plot is a square and so has four sides. The perimeter of a region is the number of sides
     * of garden plots in the region that do not touch another garden plot in the same region.
     *
     * The price of fence required for a region is found by multiplying that region's area by its perimeter.
     *
     * What is the total price of fencing all regions on your map?
     */
    fun part1(input: String): Int {
        val grid = readInput(input)
        val visited = mutableSetOf<Vector>()
        return grid.withIndex().sumOf { (y, row) ->
            row.withIndex().sumOf { (x, _) ->
                val current = grid.getLocation(Vector(x, y))!!
                if (current.vector !in visited) {
                    val plot = traversePlot(grid, current, Plot(mutableSetOf(), 0))
                    visited.addAll(plot.area)
                    plot.perimeter * plot.area.size
                } else {
                    0
                }
            }
        }
    }

    data class DiscountPlot(val area: Set<Vector>, val perimeters: MutableMap<Pair<Vector, Direction>, Int>) {
        private var perimeterId = 0

        fun nextId() = perimeterId++

        val distinctPerimeters: Int
            get() = perimeters.values.distinct().size
    }

    private fun traversePerimeter(
        plot: DiscountPlot,
        current: Vector,
    ): DiscountPlot {
        dirs
            .filter { current + it !in plot.area }
            .forEach { perimeter ->
                if (current to perimeter !in plot.perimeters) {
                    val id = plot.nextId()
                    arrayOf(perimeter.toLeft().vector, perimeter.toRight().vector).forEach { dir ->
                        var distance = 0
                        var next = current
                        while (next in plot.area && next + perimeter !in plot.area) {
                            plot.perimeters[next to perimeter] = id
                            next = current + (dir * distance++)
                        }
                    }
                }
            }

        return plot
    }

    /**
     * instead of using the perimeter to calculate the price,
     * you need to use the number of sides each region has.
     * Each straight section of fence counts as a side, regardless of how long it is.
     */
    fun part2(input: String): Int {
        val grid = readInput(input)
        val visited = mutableSetOf<Vector>()
        return grid.withIndex().sumOf { (y, row) ->
            row.withIndex().sumOf { (x, _) ->
                val current = grid.getLocation(Vector(x, y))!!
                if (current.vector !in visited) {
                    val plot = traversePlot(grid, current, Plot(mutableSetOf(), 0))
                    val discountPlot = DiscountPlot(plot.area, mutableMapOf())
                    discountPlot.area.forEach {
                        traversePerimeter(discountPlot, it)
                    }
                    visited.addAll(discountPlot.area)
                    discountPlot.distinctPerimeters * discountPlot.area.size
                } else {
                    0
                }
            }
        }
    }
}
