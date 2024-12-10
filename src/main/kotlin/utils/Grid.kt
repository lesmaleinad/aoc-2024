package com.lesmaleinad.aoc2024.utils

fun List<List<*>>.contains(vector: Vector): Boolean = this.getOrNull(vector.y)?.indices?.contains(vector.x) == true
