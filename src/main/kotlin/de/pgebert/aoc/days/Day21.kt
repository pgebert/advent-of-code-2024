package de.pgebert.aoc.days

import de.pgebert.aoc.Day
import java.util.*

class Day21(input: String? = null) : Day(21, "Day21", input) {


    private val numericPad: Map<Pair<Int, Int>, Char> = mapOf(
        Pair(0, 0) to '7', Pair(1, 0) to '8', Pair(2, 0) to '9',
        Pair(0, 1) to '4', Pair(1, 1) to '5', Pair(2, 1) to '6',
        Pair(0, 2) to '1', Pair(1, 2) to '2', Pair(2, 2) to '3',
        Pair(1, 3) to '0', Pair(2, 3) to 'A'
    )

    private val directionalPad: Map<Pair<Int, Int>, Char> = mapOf(
        Pair(1, 0) to '^', Pair(2, 0) to 'A',
        Pair(0, 1) to '<', Pair(1, 1) to 'v', Pair(2, 1) to '>'
    )

    val numericPaths = numericPad.allPaths()
    val directionalPaths = directionalPad.allPaths()

    private fun Map<Pair<Int, Int>, Char>.allPaths(): Map<Pair<Char, Char>, List<String>> =
        keys.pairwise().associate { (a, b) -> (getValue(a) to getValue(b)) to lowestCostPaths(a, b) }


    private fun <T> Collection<T>.pairwise(): List<Pair<T, T>> = buildList {
        forEach { left: T ->
            forEach { right: T -> add(Pair(left, right)) }
        }
    }

    private fun findCost(
        code: String,
        depth: Int,
        transitions: Map<Pair<Char, Char>, List<String>> = numericPaths,
        cache: MutableMap<Pair<String, Int>, Long> = mutableMapOf()
    ): Long {
        val result = cache.getOrPut(code to depth) {
            "A$code".zipWithNext().sumOf { transition ->
                val paths: List<String> = transitions.getValue(transition)
                if (depth == 0) {
                    println(paths.minBy { it.length })
                    println(paths.minOf { it.length })
                    paths.minOf { it.length }.toLong()
                } else {
                    if (depth in 1..2)
                        println(
                            "Depth $depth: ${
                                paths.minBy { path ->
                                    findCost(
                                        path,
                                        depth - 1,
                                        directionalPaths,
                                        cache
                                    )
                                }
                            } with cost ${
                                paths.minOf { path ->
                                    findCost(
                                        path,
                                        depth - 1,
                                        directionalPaths,
                                        cache
                                    )
                                }
                            }"
                        )
                    paths.minOf { path -> findCost(path, depth - 1, directionalPaths, cache) }
                }
            }
        }

        return result
    }


    private fun Map<Pair<Int, Int>, Char>.lowestCostPaths(start: Pair<Int, Int>, end: Pair<Int, Int>): List<String> {

        val directions = mapOf(
            Pair(1, 0) to '>',  // Right
            Pair(0, 1) to 'v',  // Down
            Pair(-1, 0) to '<', // Left
            Pair(0, -1) to '^'  // Up
        )

        val queue: Queue<Pair<Pair<Int, Int>, String>> = LinkedList()

        var lowestCost = Int.MAX_VALUE
        val lowestCostPaths = mutableListOf<String>()

        queue.add(start to "")

        while (queue.isNotEmpty()) {
            val (current, path) = queue.poll()

            if (current == end) {

                if (path.length < lowestCost) {
                    lowestCost = path.length
                    lowestCostPaths.add(path + 'A')
                } else if (path.length == lowestCost) {
                    lowestCostPaths.add(path + 'A')
                }
                continue
            }

            if (path.length > lowestCost) {
                continue
            }

            for ((dir, value) in directions) {
                val neighbor = Pair(current.first + dir.first, current.second + dir.second)
                if (neighbor in this) {
                    queue.add(neighbor to path + value)
                }
            }
        }

        return lowestCostPaths
    }


    override fun partOne(): Long = inputList.sumOf { findCost(it, 2) * it.dropLast(1).toLong() }

    override fun partTwo() = inputList.sumOf { findCost(it, 25) * it.dropLast(1).toLong() }
}
