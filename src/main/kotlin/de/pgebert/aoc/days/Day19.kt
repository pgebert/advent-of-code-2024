package de.pgebert.aoc.days

import de.pgebert.aoc.Day

class Day19(input: String? = null) : Day(19, "Day19", input) {

    private val patterns = inputList.first().split(",").map { it.trim() }
    private val designs = inputList.filter { it.isNotBlank() }.drop(1)

    override fun partOne() =
        designs.count { it.makeDesign() > 0 }

    override fun partTwo() =
        designs.sumOf { it.makeDesign() }

    private fun String.makeDesign(cache: MutableMap<String, Long> = mutableMapOf()): Long =
        if (isEmpty()) 1
        else cache.getOrPut(this) {
            patterns.filter { startsWith(it) }.sumOf {
                removePrefix(it).makeDesign(cache)
            }
        }
}
