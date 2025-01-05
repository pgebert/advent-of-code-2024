package de.pgebert.aoc.days

import de.pgebert.aoc.Day

class Day05(input: String? = null) : Day(5, "Day5", input) {

    val ruleRegex = "(\\d+)\\|(\\d+)".toRegex()

    private val rules =
        inputList.mapNotNull { line ->
            ruleRegex.find(line)?.destructured?.let { (predecessor, successor) ->
                predecessor.toInt() to successor.toInt()
            }
        }.groupBy({ it.first }, { it.second })
            .mapValues { (_, successors) -> successors.toSet() }

    private val updates = inputList
        .filter { it.contains(",") }
        .map { it.split(",").map(String::toInt) }


    override fun partOne() =
        updates.filter { it.isValidUpdate() }.sumOf { it[it.size / 2] }

    override fun partTwo() =
        updates.filterNot { it.isValidUpdate() }.map { update ->
            val correctedUpdate = mutableListOf<Int>()
            outer@ for (i in update.indices) {
                val successors = rules[update[i]] ?: emptyList()

                for (j in correctedUpdate.indices) {
                    if (correctedUpdate[j] in successors) {
                        correctedUpdate.add(j, update[i])
                        continue@outer
                    }
                }

                correctedUpdate.add(update[i])
            }
            correctedUpdate

        }.sumOf { it[it.size / 2] }

    private fun List<Int>.isValidUpdate() = rules.all { (predecessor, successors) ->
        successors.all { successor -> indexOf(successor) == -1 || indexOf(predecessor) < indexOf(successor) }
    }
}
