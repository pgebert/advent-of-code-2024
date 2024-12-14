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


                println("${inputList[i][j]},$area,$perimeter, $result")


            }
        }

        return result
    }

    private val directions = listOf(
        -1 to 0,  // up
        0 to 1,   // right
        1 to 0,   // down
        0 to -1   // left
    )


    private fun getAreaAndPerimeter(pos: Pair<Int, Int>, visited: MutableSet<Pair<Int, Int>>): Pair<Int, Int> {

        var areaSize = 1
        var perimeterSize = 0
        visited.add(pos)

        directions.forEach { dir ->
            val newPos = Pair(pos.first + dir.first, pos.second + dir.second)
            if (
                !newPos.withinBounds()
                || inputList[newPos.first][newPos.second] != inputList[pos.first][pos.second]
            ) {
                perimeterSize++
            }
        }

        directions.forEach { dir ->
            val newPos = Pair(pos.first + dir.first, pos.second + dir.second)
            if (newPos !in visited
                && newPos.withinBounds()
                && inputList[newPos.first][newPos.second] == inputList[pos.first][pos.second]
            ) {
                visited.add(newPos)
                val (newArea, newPerimeter) = getAreaAndPerimeter(newPos, visited)
                areaSize += newArea
                perimeterSize += newPerimeter
            }
        }

        return areaSize to perimeterSize

    }


    private fun Pair<Int, Int>.withinBounds() =
        first in inputList.indices && second in inputList[first].indices

    override fun partTwo(): Int {

        var result = 0

        val visited = mutableSetOf<Pair<Int, Int>>()

        for (i in inputList.indices) {
            for (j in inputList[i].indices) {

                if (Pair(i, j) in visited) continue
                val (area, perimeter) = getAreaAndPerimeterDiscounted(i to j, visited)

                result += (area * perimeter)


                println("${inputList[i][j]},$area,$perimeter, $result")


            }
        }

        return result
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

            // outer corner
            if ((!newPos1.withinBounds() || inputList[newPos1.first][newPos1.second] != inputList[pos.first][pos.second])
                && (!newPos2.withinBounds() || inputList[newPos2.first][newPos2.second] != inputList[pos.first][pos.second])
            ) {
                perimeterSize++
            }

            // inner corner
            else if ((newPos1.withinBounds() && inputList[newPos1.first][newPos1.second] == inputList[pos.first][pos.second])
                && (newPos2.withinBounds() && inputList[newPos2.first][newPos2.second] == inputList[pos.first][pos.second])
                && (inputList[newPos3.first][newPos3.second] != inputList[pos.first][pos.second])
            ) {
                perimeterSize++
            }
        }


        directions.forEach { dir ->

            // estimate Area
            val newPos = Pair(pos.first + dir.first, pos.second + dir.second)
            if (newPos !in visited
                && newPos.withinBounds()
                && inputList[newPos.first][newPos.second] == inputList[pos.first][pos.second]
            ) {
                visited.add(newPos)
                val (newArea, newPerimeter) = getAreaAndPerimeterDiscounted(newPos, visited)
                areaSize += newArea
                perimeterSize += newPerimeter
            }
        }

        return areaSize to perimeterSize

    }
}


