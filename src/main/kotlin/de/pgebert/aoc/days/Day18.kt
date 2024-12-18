package de.pgebert.aoc.days

import de.pgebert.aoc.Day
import java.util.*

class Day18(input: String? = null) : Day(18, "Day18", input) {

    override fun partOne() = partOne(71, 1024)

    fun partOne(size: Int, takeFirst: Int): Int {

        val bytes = inputList.map { it.split(",").map { it.toInt() }.let { it[1] to it[0] } }.take(takeFirst).toSet()

        val start = 0 to 0
        val target = size - 1 to size - 1

        val shortestPath = bfs(start, target, bytes, size)

        return shortestPath.size - 1

    }

    override fun partTwo(): String = partTwo(71, 1024)

    fun partTwo(size: Int, takeFirst: Int): String {

        val all = inputList.map { it.split(",").map { it.toInt() }.let { it[1] to it[0] } }

        for (i in takeFirst..all.size) {

            val bytes = all.take(i).toSet()

            val start = 0 to 0
            val target = size - 1 to size - 1

            runCatching {
                bfs(start, target, bytes, size)
            }.onFailure {
                return all.elementAt(i - 1).let { "${it.second},${it.first}" }
            }
        }

        throw IllegalStateException("Path not blocked")
    }

    private fun bfs(
        start: Pair<Int, Int>,
        target: Pair<Int, Int>,
        blocked: Set<Pair<Int, Int>>,
        size: Int
    ): List<Pair<Int, Int>> {
        val queue: Queue<List<Pair<Int, Int>>> = LinkedList()
        val visited = mutableSetOf<Pair<Int, Int>>()

        queue.add(listOf(start))

        while (queue.isNotEmpty()) {
            val path = queue.poll()
            val node = path.last()

            if (node == target) {
                return path
            }

            if (!visited.contains(node)) {
                visited.add(node)
                val neighbors = node.getNeighbors(size)
                neighbors.forEach { neighbor ->
                    if (neighbor !in blocked) {
                        val newPath = path + neighbor
                        queue.add(newPath)
                    }
                }
            }
        }
        throw IllegalStateException("No path found")
    }

    private fun Pair<Int, Int>.getNeighbors(size: Int) = buildList {
        directions.forEach { dir ->
            if (first + dir.first in 0 until size && second + dir.second in 0 until size) {
                add(first + dir.first to second + dir.second)
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
