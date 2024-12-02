package de.pgebert.aoc.days

import de.pgebert.aoc.Day
import kotlin.math.abs

class Day02(input: String? = null) : Day(2, "Day2", input) {


    override fun partOne() = parseInput()
        .filter { report -> report.isSortedOrSortedDescending() }
        .filter { report -> report.hasConsecutiveDifferencesWithinRange(1..3) }
        .size


    override fun partTwo() = parseInput()
        .map { report ->
            report.indices.map { index ->
                report.filterIndexed { i, _ -> i != index }
            }
        }
        .map { report ->
            report.filter { it.isSortedOrSortedDescending() }
        }
        .filter { report ->
            report.any { it.hasConsecutiveDifferencesWithinRange(1..3) }
        }
        .size

    private fun parseInput() = inputList.map {
        it.split(" ").filterNot { it.isBlank() }.map { it.toInt() }
    }

    private fun List<Int>.isSortedOrSortedDescending(): Boolean {
        return this == this.sorted() || this == this.sortedDescending()
    }

    private fun List<Int>.hasConsecutiveDifferencesWithinRange(range: IntRange): Boolean {
        return this.zipWithNext().all { (first, second) -> abs(first - second) in range }
    }
}
