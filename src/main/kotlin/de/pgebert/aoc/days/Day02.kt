package de.pgebert.aoc.days

import de.pgebert.aoc.Day
import kotlin.math.abs

class Day02(input: String? = null) : Day(2, "Day2", input) {


    override fun partOne() = parseInput()
        .filter { report -> report.isSortedOrSortedDescending() }
        .filter { report -> report.hasConsecutiveDifferencesWithinRange(1..3) }
        .size


    override fun partTwo() = parseInput()
        .map { report -> generateFilteredReports(report) }
        .map { filteredReports -> filterSortedReports(filteredReports) }
        .filter { sortedReports -> hasValidConsecutiveDifferences(sortedReports) }
        .size


    private fun generateFilteredReports(report: List<Int>) =
        report.indices.map { index -> report.filterIndexed { i, _ -> i != index } }

    private fun filterSortedReports(reports: List<List<Int>>) =
        reports.filter { it.isSortedOrSortedDescending() }

    private fun hasValidConsecutiveDifferences(reports: List<List<Int>>) =
        reports.any { it.hasConsecutiveDifferencesWithinRange(1..3) }

    private fun parseInput() = inputList.map {
        it.split(" ").filterNot { it.isBlank() }.map { it.toInt() }
    }

    private fun List<Int>.isSortedOrSortedDescending() =
        this == sorted() || this == sortedDescending()


    private fun List<Int>.hasConsecutiveDifferencesWithinRange(range: IntRange) =
        zipWithNext().all { (first, second) -> abs(first - second) in range }

}
