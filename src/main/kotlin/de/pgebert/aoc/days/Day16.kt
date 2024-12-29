package de.pgebert.aoc.days

import de.pgebert.aoc.Day
import kotlin.math.abs

class Day16(input: String? = null) : Day(16, "Day16", input) {

    override fun partOne(): Int {


        val maze = inputList.toList()

        var start = Pair(0, 0)
        var end = Pair(0, 0)
        val dir = Pair(0, 1)
        val walls = mutableSetOf<Pair<Int, Int>>()

        for (i in maze.indices) {
            for (j in maze[i].indices) {
                when (maze[i][j]) {
                    'S' -> start = Pair(i, j)
                    'E' -> end = Pair(i, j)
                    '#' -> walls.add(Pair(i, j))
                }
            }
        }

        val totalCost = move(start, dir, mutableMapOf(), 0, maze)

        return totalCost
    }

    private fun move(
        pos: Pair<Int, Int>,
        dir: Pair<Int, Int>,
        globalCostMap: MutableMap<Int, Int>,
        cost: Int,
        maze: List<String>
    ): Int {

        if (maze[pos.first][pos.second] == 'E') return cost

        var minCost = Int.MAX_VALUE

        val dirIndex = directions.indexOf(dir)
        for (i in -1..1) {

            val newDir = directions[(dirIndex + i + directions.size) % directions.size]
            val newPos = pos + newDir
            val newCost = cost + 1000 * abs(i) + 1

            if (cost > 111484) continue

            // + abs(((dirIndex + i + directions.size) % directions.size) - 1) * 1000
            if (maze[pos.first][pos.second] == '#') continue
            if (globalCostMap.getOrDefault(hash(newPos, newDir), Int.MAX_VALUE) <= newCost) continue
//            if (history.getOrDefault(
//                    newPos,
//                    Int.MAX_VALUE
//                ) < newCost + abs(((dirIndex + i + directions.size) % directions.size) - 1) * 1000
//            ) continue

            globalCostMap[hash(newPos, newDir)] = newCost


            val costToEnd = move(newPos, newDir, globalCostMap, newCost, maze)

            if (costToEnd < minCost) {
                minCost = costToEnd
            }
        }

        return minCost
    }

    private fun hash(pos: Pair<Int, Int>, dir: Pair<Int, Int>): Int {

        val height = inputList.size
        val width = inputList.first().length

        val i = directions.indexOf(dir)

        return (pos.first * width + pos.second) * 4 + i

    }

    private operator fun Pair<Int, Int>.plus(other: Pair<Int, Int>) = Pair(first + other.first, second + other.second)

    private val directions = listOf(
        -1 to 0,  // up
        0 to 1,   // right
        1 to 0,   // down
        0 to -1   // left
    )

    override fun partTwo(): Int {


        val maze = inputList.toList()

        var start = Pair(0, 0)
        var end = Pair(0, 0)
        val dir = Pair(0, 1)
        val walls = mutableSetOf<Pair<Int, Int>>()

        for (i in maze.indices) {
            for (j in maze[i].indices) {
                when (maze[i][j]) {
                    'S' -> start = Pair(i, j)
                    'E' -> end = Pair(i, j)
                    '#' -> walls.add(Pair(i, j))
                }
            }
        }

        val paths = getPaths(start, dir, mutableMapOf(), 0, setOf(start), maze)

        return paths.size
    }


    private fun getPaths(
        pos: Pair<Int, Int>,
        dir: Pair<Int, Int>,
        globalCostMap: MutableMap<Int, Int>,
        cost: Int,
        path: Set<Pair<Int, Int>>,
        maze: List<String>
    ): Set<Pair<Int, Int>> {

        if (maze[pos.first][pos.second] == 'E') {
            if (cost == 111480) return path + pos
            else emptySet<Pair<Int, Int>>()
        }

        var paths = mutableSetOf<Pair<Int, Int>>()

        val dirIndex = directions.indexOf(dir)
        for (i in -1..1) {

            val newDir = directions[(dirIndex + i + directions.size) % directions.size]
            val newPos = pos + newDir
            val newCost = cost + 1000 * abs(i) + 1

            if (cost > 111484) continue

            // + abs(((dirIndex + i + directions.size) % directions.size) - 1) * 1000
            if (maze[pos.first][pos.second] == '#') continue
            if (globalCostMap.getOrDefault(hash(newPos, newDir), Int.MAX_VALUE) < newCost) continue
//            if (history.getOrDefault(
//                    newPos,
//                    Int.MAX_VALUE
//                ) < newCost + abs(((dirIndex + i + directions.size) % directions.size) - 1) * 1000
//            ) continue

            globalCostMap[hash(newPos, newDir)] = newCost


            paths.addAll(getPaths(newPos, newDir, globalCostMap, newCost, path + newPos, maze))
        }

        return paths
    }
}
