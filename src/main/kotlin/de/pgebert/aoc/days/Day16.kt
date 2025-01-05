package de.pgebert.aoc.days

import de.pgebert.aoc.Day
import java.util.*
import kotlin.math.abs

class Day16(input: String? = null) : Day(16, "Day16", input) {
    
    override fun partOne(): Int {

        val (start, end, walls) = findPositions(inputList)
        val dir = Pair(0, 1)

        val paths = findMinCostPaths(start, dir, end, walls)
        return paths.first().second
    }


    override fun partTwo(): Int {

        val (start, end, walls) = findPositions(inputList)
        val dir = Pair(0, 1)

        val paths = findMinCostPaths(start, dir, end, walls)
        return paths.flatMap { it.first }.toSet().size
    }

    private fun findPositions(maze: List<String>): Triple<Pair<Int, Int>, Pair<Int, Int>, Set<Pair<Int, Int>>> {
        var start = Pair(0, 0)
        var end = Pair(0, 0)
        val walls = mutableSetOf<Pair<Int, Int>>()

        maze.forEachIndexed { i, row ->
            row.forEachIndexed { j, cell ->
                when (cell) {
                    'S' -> start = Pair(i, j)
                    'E' -> end = Pair(i, j)
                    '#' -> walls.add(Pair(i, j))
                }
            }
        }

        return Triple(start, end, walls)
    }

    private fun findMinCostPaths(
        start: Pair<Int, Int>,
        direction: Pair<Int, Int>,
        end: Pair<Int, Int>,
        blocked: Set<Pair<Int, Int>>,
    ): List<Pair<List<Pair<Int, Int>>, Int>> {

        val costCache = mutableMapOf<Pair<Pair<Int, Int>, Pair<Int, Int>>, Int>()

        // Stack for DFS: (current position, current direction, current cost)
        val stack: Stack<Triple<List<Pair<Int, Int>>, Pair<Int, Int>, Int>> = Stack()

        stack.push(Triple(listOf(start), direction, 0))

        var minCost = Int.MAX_VALUE
        val minCostPaths = mutableListOf<List<Pair<Int, Int>>>()

        while (stack.isNotEmpty()) {
            val (path, currentDir, currentCost) = stack.pop()
            val currentPos = path.last()

            if (currentPos == end) {
                if (currentCost < minCost) {
                    minCost = currentCost
                    minCostPaths.removeAll { true }
                }
                if (currentCost == minCost) {
                    minCostPaths.add(path)
                }
            }

            if (currentCost >= minCost) {
                continue
            }

            val cachedCost = costCache.getOrDefault(currentPos to currentDir, Int.MAX_VALUE)
            if (currentCost > cachedCost) {
                continue
            }

            costCache[currentPos to currentDir] = currentCost

            val neighbors = getNeighbors(currentPos, currentDir)
            neighbors.forEach { (neighborPos, neighborDir, neighborCost) ->
                if (neighborPos !in blocked) {
                    stack.push(Triple(path + neighborPos, neighborDir, currentCost + neighborCost))
                }
            }
        }

        return minCostPaths.map { it to minCost }
    }


    private fun getNeighbors(pos: Pair<Int, Int>, dir: Pair<Int, Int>) = buildList {

        val dirIndex = directions.indexOf(dir)
        for (i in -1..1) {

            val newDir = directions[(dirIndex + i + directions.size) % directions.size]
            val newPos = pos + newDir
            val cost = 1000 * abs(i) + 1

            add(Triple(newPos, newDir, cost))
        }
    }

    private operator fun Pair<Int, Int>.plus(other: Pair<Int, Int>) =
        Pair(first + other.first, second + other.second)

    private val directions = listOf(
        -1 to 0,  // up
        0 to 1,   // right
        1 to 0,   // down
        0 to -1   // left
    )

}
