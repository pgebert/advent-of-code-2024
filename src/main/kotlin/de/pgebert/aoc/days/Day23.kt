package de.pgebert.aoc.days

import de.pgebert.aoc.Day

class Day23(input: String? = null) : Day(23, "Day23", input) {

    override fun partOne() =
        connected
            .filterKeys { it.startsWith("t") }
            .flatMap { (a, value) ->
                value
                    .combinations()
                    .filter { (b, c) -> connected[b]?.contains(c) == true }
                    .map { (b, c) -> setOf(a, b, c) }
            }.distinct()
            .size

    override fun partTwo() = connected.keys.map {
        buildSet {
            add(it)
            connected.forEach { (node, connectedNodes) ->
                if (all { it in connectedNodes }) {
                    add(node)
                }
            }
        }
    }.maxBy { it.size }.sortedBy { it }.joinToString(",")


    private fun String.toBidirectionalPairs(): List<Pair<String, String>> =
        split("-").let { listOf(it.first() to it.last(), it.last() to it.first()) }

    private val connected = inputList
        .flatMap { it.toBidirectionalPairs() }
        .groupBy { it.first }
        .mapValues { entry -> entry.value.map { it.second } }


    private fun <T> List<T>.combinations(): Sequence<Pair<T, T>> = sequence {
        for (i in indices) {
            for (j in i + 1 until size) {
                yield(get(i) to get(j))
            }
        }
    }
}
