package com.lesmaleinad.aoc2024.utils

fun <T> List<T>.fromTheEnd(index: Int): T? = get(size - 1 - index)
