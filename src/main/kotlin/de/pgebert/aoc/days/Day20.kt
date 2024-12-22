package de.pgebert.aoc.days

import de.pgebert.aoc.Day
import kotlin.math.abs

class Day20(input: String? = null) : Day(20, "Day20", input) {

    val start = positionsOf('S').first()
    val walls = positionsOf('#')

    override fun partOne() = countCheats(cheatLength = 2, minTimeSaving = 100)
    fun partOne(minSaving: Int) = countCheats(cheatLength = 2, minTimeSaving = minSaving)

    override fun partTwo() = countCheats(cheatLength = 20, minTimeSaving = 100)
    fun partTwo(minSaving: Int) = countCheats(cheatLength = 20, minTimeSaving = minSaving)

    private fun countCheats(cheatLength: Int, minTimeSaving: Int): Int {
        val path = mutableListOf(start)
        val visited = mutableSetOf(start)
        val toVisit = mutableListOf(start)

        while (toVisit.isNotEmpty()) {
            val current = toVisit.removeFirst()
            current.neighbours().forEach { n ->
                if (n !in walls && n !in visited) {
                    toVisit += n
                    visited += n
                    path += n
                }
            }
        }

        return path.indices.sumOf { i ->
            (i + minTimeSaving..path.lastIndex).count { j ->
                val d = path[i] distanceTo path[j]
                d <= cheatLength && j - i - d >= minTimeSaving
            }
        }
    }

    private fun positionsOf(char: Char): List<Pair<Int, Int>> = buildList {
        for (i in inputList.indices) {
            for (j in inputList[i].indices) {
                if (inputList[i][j] == char) add(i to j)
            }
        }
    }

    private infix fun Pair<Int, Int>.distanceTo(other: Pair<Int, Int>) =
        abs(first - other.first) + abs(second - other.second)


    private fun Pair<Int, Int>.neighbours() = buildList {
        directions.forEach { (dx, dy) ->
            val newX = first + dx
            val newY = second + dy
            if (newX in inputList.indices && newY in inputList[newX].indices) {
                add(newX to newY)
            }
        }
    }

    private val directions = listOf(
        -1 to 0,  // up
        0 to 1,   // right
        1 to 0,   // down
        0 to -1   // left
    )
}
