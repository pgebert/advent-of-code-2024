package de.pgebert.aoc.days

import de.pgebert.aoc.Day

class Day03(input: String? = null) : Day(3, "Day3", input) {

    private val mulRegex = "mul\\((\\d{1,3}),(\\d{1,3})\\)".toRegex()
    private val condRegex = "(mul\\((\\d{1,3}),(\\d{1,3})\\)|do\\(\\)|don't\\(\\))".toRegex()

    override fun partOne() = mulRegex
        .findAll(inputString)
        .sumOf { it.groupValues[1].toInt() * it.groupValues[2].toInt() }


    override fun partTwo() = condRegex
        .findAll(inputString)
        .fold(Pair(0, true)) { acc, match ->
            when {
                match.value == "do()" -> Pair(acc.first, true)
                match.value == "don't()" -> Pair(acc.first, false)
                acc.second -> Pair(acc.first + match.groupValues[2].toInt() * match.groupValues[3].toInt(), true)
                else -> acc
            }
        }.first

}
