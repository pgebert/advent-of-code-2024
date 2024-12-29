package de.pgebert.aoc.days

import de.pgebert.aoc.Day
import kotlin.math.abs

class Day01(input: String? = null) : Day(1, "Day1", input) {

    val list1 = inputList.map { it.split(" ").first().toInt() }
    val list2 = inputList.map { it.split(" ").last().toInt() }

    override fun partOne() = list1.sorted()
        .zip(list2.sorted())
        .sumOf { (a, b) ->
            abs(a - b)
        }

    override fun partTwo() = list1.sumOf { a ->
        a * list2.count { it == a }
    }
}
