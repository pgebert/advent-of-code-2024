package de.pgebert.aoc.days

import de.pgebert.aoc.Day

class Day12(input: String? = null) : Day(12, "Day12", input) {

    override fun partOne(): Int {

        var result = 0
        val visited = mutableSetOf<Pair<Int, Int>>()

        for (i in inputList.indices) {
            for (j in inputList[i].indices) {
                if (Pair(i, j) in visited) continue
                val (area, perimeter) = getAreaAndPerimeter(i to j, visited)
                result += (area * perimeter)
            }
        }

        return result
    }

    override fun partTwo(): Int {

        var result = 0
        val visited = mutableSetOf<Pair<Int, Int>>()

        for (i in inputList.indices) {
            for (j in inputList[i].indices) {
                if (Pair(i, j) in visited) continue
                val (area, perimeter) = getAreaAndPerimeterDiscounted(i to j, visited)
                result += (area * perimeter)
            }
        }
        return result
    }


    private fun getAreaAndPerimeter(pos: Pair<Int, Int>, visited: MutableSet<Pair<Int, Int>>): Pair<Int, Int> {

        var areaSize = 1
        var perimeterSize = 0
        visited.add(pos)

        directions.forEach { dir ->
            val newPos = Pair(pos.first + dir.first, pos.second + dir.second)
            if (isSameValue(newPos, pos)) {
                if (newPos !in visited) {
                    val (newArea, newPerimeter) = getAreaAndPerimeter(newPos, visited)
                    areaSize += newArea
                    perimeterSize += newPerimeter
                }
            } else {
                perimeterSize++
            }
        }
        return areaSize to perimeterSize
    }


    private fun getAreaAndPerimeterDiscounted(
        pos: Pair<Int, Int>,
        visited: MutableSet<Pair<Int, Int>>
    ): Pair<Int, Int> {

        var areaSize = 1
        var perimeterSize = 0
        visited.add(pos)

        directions.plus(directions.first()).zipWithNext().forEach { (dir1, dir2) ->
            val newPos1 = Pair(pos.first + dir1.first, pos.second + dir1.second)
            val newPos2 = Pair(pos.first + dir2.first, pos.second + dir2.second)
            val newPos3 = Pair(pos.first + dir1.first + dir2.first, pos.second + dir1.second + dir2.second)

            val outerCorner = isDifferentValue(newPos1, pos) && isDifferentValue(newPos2, pos)
            val innerCorner = isSameValue(newPos1, pos) && isSameValue(newPos2, pos) && isDifferentValue(newPos3, pos)

            if (innerCorner || outerCorner) {
                perimeterSize++
            }
        }


        directions.forEach { dir ->
            val newPos = Pair(pos.first + dir.first, pos.second + dir.second)
            if (newPos !in visited
                && newPos.withinBounds()
                && inputList[newPos.first][newPos.second] == inputList[pos.first][pos.second]
            ) {
                val (newArea, newPerimeter) = getAreaAndPerimeterDiscounted(newPos, visited)
                areaSize += newArea
                perimeterSize += newPerimeter
            }
        }

        return areaSize to perimeterSize
    }

    private val directions = listOf(
        -1 to 0,  // up
        0 to 1,   // right
        1 to 0,   // down
        0 to -1   // left
    )

    private fun Pair<Int, Int>.withinBounds() =
        first in inputList.indices && second in inputList[first].indices

    private fun isDifferentValue(pos1: Pair<Int, Int>, pos2: Pair<Int, Int>) =
        !pos1.withinBounds() || inputList[pos1.first][pos1.second] != inputList[pos2.first][pos2.second]

    private fun isSameValue(pos1: Pair<Int, Int>, pos2: Pair<Int, Int>) =
        pos1.withinBounds() && inputList[pos1.first][pos1.second] == inputList[pos2.first][pos2.second]

}


