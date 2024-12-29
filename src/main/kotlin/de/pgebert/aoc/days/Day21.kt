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

//    private val directions = mapOf(
//        Pair(0, 1) to '>',  // Right
//        Pair(1, 0) to 'v',  // Down
//        Pair(0, -1) to '<', // Left
//        Pair(-1, 0) to '^'  // Up
//    )

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

//        println("Depth $depth: $result")

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

//    private fun Map<Pair<Int, Int>, Char>.lowestCostPaths(
//        start: Pair<Int, Int>,
//        end: Pair<Int, Int>
//    ): List<String> {
//        val queue = PriorityQueue<Pair<List<Pair<Int, Int>>, Int>>(compareBy { it.second })
//            .apply { add(listOf(start) to 0) }
//        val seen = mutableMapOf<Pair<Int, Int>, Int>()
//        var costAtGoal: Int? = null
//        val allPaths: MutableList<String> = mutableListOf()
//
//        while (queue.isNotEmpty()) {
//            val (path, cost) = queue.poll()
//            val location = path.last()
//
//            if (costAtGoal != null && cost > costAtGoal) {
//                return allPaths
//            } else if (path.last() == end) {
//                costAtGoal = cost
//                allPaths.add(path.zipWithNext().map { (from, to) -> from.diffToChar(to) }.joinToString("") + "A")
//            } else if (seen.getOrDefault(location, Int.MAX_VALUE) >= cost) {
//                seen[location] = cost
//                location
//                    .getNeighbors()
//                    .filter { it in keys }
//                    .forEach { queue.add(path + it to cost + 1) }
//            }
//        }
//        return allPaths
//    }


    fun Pair<Int, Int>.getNeighbors() = buildList {

        val directions = listOf(
            -1 to 0,  // up
            0 to 1,   // right
            1 to 0,   // down
            0 to -1   // left
        )

        directions.forEach { dir ->
            add(first + dir.first to second + dir.second)
        }
    }

    private fun Pair<Int, Int>.diffToChar(other: Pair<Int, Int>): Char =
        when {
            second > other.second -> '^'
            first < other.first -> '>'
            second < other.second -> 'v'
            first > other.first -> '<'
            else -> throw IllegalArgumentException("Invalid direction from $this to $other")
        }


    override fun partOne(): Long = inputList.sumOf { findCost(it, 2) * it.dropLast(1).toLong() }

    override fun partTwo() = inputList.sumOf { findCost(it, 25) * it.dropLast(1).toLong() }
}
