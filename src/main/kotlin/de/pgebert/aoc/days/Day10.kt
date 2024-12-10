package de.pgebert.aoc.days

import de.pgebert.aoc.Day

class Day10(input: String? = null) : Day(10, "Day10", input) {

    private val directions = listOf(
        -1 to 0,  // up
        0 to 1,   // right
        1 to 0,   // down
        0 to -1   // left
    )

    override fun partOne() =
        inputList.indices.sumOf { i ->
            inputList[i].indices.sumOf { j ->
                if (inputList[i][j] == '0') followTrail(i to j).size else 0
            }
        }


    override fun partTwo() =
        inputList.indices.sumOf { i ->
            inputList[i].indices.sumOf { j ->
                if (inputList[i][j] == '0') possibleTrails(i to j) else 0
            }
        }


    private fun followTrail(pos: Pair<Int, Int>): Set<Pair<Int, Int>> {
        val targets = mutableSetOf<Pair<Int, Int>>()
        exploreTrails(pos) { newPos ->
            if (inputList[newPos.first][newPos.second] == '9') {
                targets.add(newPos)
            } else {
                targets.addAll(followTrail(newPos))
            }
        }
        return targets
    }

    private fun possibleTrails(pos: Pair<Int, Int>): Int {
        var trails = 0
        exploreTrails(pos) { newPos ->
            trails += if (inputList[newPos.first][newPos.second] == '9') {
                1
            } else {
                possibleTrails(newPos)
            }
        }
        return trails
    }

    private inline fun exploreTrails(pos: Pair<Int, Int>, action: (Pair<Int, Int>) -> Unit) {
        directions.forEach { dir ->
            val newPos = pos.first + dir.first to pos.second + dir.second
            if (newPos.withinBounds() && inputList[newPos.first][newPos.second] - inputList[pos.first][pos.second] == 1) {
                action(newPos)
            }
        }
    }

    private fun Pair<Int, Int>.withinBounds() = first in inputList.indices && second in inputList[first].indices
}