package de.pgebert.aoc.days

import de.pgebert.aoc.Day

class Day22(input: String? = null) : Day(22, "Day22", input) {

    override fun partOne() = inputList.sumOf { it.toLong().secretNumbers().elementAt(2000) }

    override fun partTwo() = buildMap {
        inputList
            .map { it.toLong().secretNumbers().take(2001).map { it.mod(10) } }
            .forEach { sequence ->
                sequence
                    .windowed(5, 1)
                    .map { it.zipWithNext { first, second -> second - first } to it.last() }
                    .distinctBy { it.first }
                    .forEach {
                        put(it.first, getOrDefault(it.first, 0) + it.second)
                    }
            }
    }.maxOf { it.value }

    private fun Long.secretNumbers() = generateSequence(this) { secret ->
        secret
            .mix { it * 64 }
            .mix { it / 32 }
            .mix { it * 2048 }
    }

    private fun Long.mix(block: (Long) -> Long) = xor(block(this)).mod(16777216L)
}
