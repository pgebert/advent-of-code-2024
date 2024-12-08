package de.pgebert.aoc.days

import de.pgebert.aoc.Day


class Day06(input: String? = null) : Day(6, "Day6", input) {


    private val directionMap = mapOf(
        '^' to Pair(-1, 0),
        '>' to Pair(0, 1),
        '<' to Pair(0, -1),
        '⌄' to Pair(1, 0)
    )

    private val nextDirectionMap = mapOf(
        Pair(-1, 0) to Pair(0, 1),
        Pair(0, 1) to Pair(1, 0),
        Pair(1, 0) to Pair(0, -1),
        Pair(0, -1) to Pair(-1, 0)
    )

    private val startPosition = getStartPosition()
    private val startDirection = getStartDirection()

    override fun partOne() = getNumberVisitedPositions(startPosition, startDirection, inputList)


    override fun partTwo() = allIndices(inputList.size)
        .filterNot { (i, j) -> inputList[i][j] in listOf('#', '^', '>', '<', '⌄') }
        .filter { (i, j) -> endsInLoopOnField(startPosition, startDirection, fieldWithBlockOnPosition(i, j)) }
        .count()

    private fun allIndices(size: Int) = sequence {
        for (i in 0 until size) {
            for (j in 0 until size) {
                yield(i to j)
            }
        }
    }

    private fun getNumberVisitedPositions(
        position: Pair<Int, Int>,
        direction: Pair<Int, Int>,
        adaptedField: List<String>,
    ): Int {
        var (currentRow, currentCol) = position
        var (dirRow, dirCol) = direction
        val history = mutableSetOf(position)

        val rowIndices = adaptedField.indices
        val colIndices = adaptedField.indices

        while (currentRow in rowIndices && currentCol in colIndices) {

            history.add(currentRow to currentCol)

            var nextRow = currentRow + dirRow
            var nextCol = currentCol + dirCol

            while (nextRow in rowIndices && nextCol in colIndices && adaptedField[nextRow][nextCol] == '#') {
                val nextDirection = nextDirectionMap[dirRow to dirCol]!!
                dirRow = nextDirection.first
                dirCol = nextDirection.second
                nextRow = currentRow + dirRow
                nextCol = currentCol + dirCol
            }

            currentRow = nextRow
            currentCol = nextCol

        }
        return history.size
    }

    private fun endsInLoopOnField(
        position: Pair<Int, Int>,
        direction: Pair<Int, Int>,
        adaptedField: List<String>,
    ): Boolean {
        var (currentRow, currentCol) = position
        var (dirRow, dirCol) = direction
        val history = mutableSetOf(Pair(position, direction))

        val rowIndices = adaptedField.indices
        val colIndices = adaptedField.indices

        while (currentRow in rowIndices && currentCol in colIndices) {
            var nextRow = currentRow + dirRow
            var nextCol = currentCol + dirCol

            while (nextRow in rowIndices && nextCol in colIndices && adaptedField[nextRow][nextCol] == '#') {
                val nextDirection = nextDirectionMap[dirRow to dirCol]!!
                dirRow = nextDirection.first
                dirCol = nextDirection.second
                nextRow = currentRow + dirRow
                nextCol = currentCol + dirCol
            }

            currentRow = nextRow
            currentCol = nextCol

            if (Pair(currentRow, currentCol) to Pair(dirRow, dirCol) in history) {
                return true
            }

            history.add(Pair(currentRow, currentCol) to Pair(dirRow, dirCol))
        }
        return false
    }

    private fun fieldWithBlockOnPosition(i: Int, j: Int) = inputList.mapIndexed { index, row ->
        if (index == i) row.replaceRange(j, j + 1, "#") else row
    }


    private fun getStartPosition(): Pair<Int, Int> {

        inputList.forEachIndexed { i, row ->
            row.forEachIndexed { j, c ->
                directionMap[c]?.let {
                    return Pair(i, j)
                }
            }
        }

        throw IllegalStateException("No valid start position found")
    }


    private fun getStartDirection(): Pair<Int, Int> {

        inputList.forEachIndexed { i, row ->
            row.forEachIndexed { j, c ->
                directionMap[c]?.let { direction ->
                    return direction
                }
            }
        }

        throw IllegalStateException("No valid start position found")
    }
}
