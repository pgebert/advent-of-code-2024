package de.pgebert.aoc.days

import de.pgebert.aoc.Day

class Day04(input: String? = null) : Day(4, "Day4", input) {

    private val field = inputList.toList()

    override fun partOne(): Int {

        val targetWord = "XMAS"
        val targetLength = targetWord.length
        var result = 0

        fun isMatch(i: Int, j: Int, di: Int, dj: Int): Boolean {
            for (k in 0 until targetLength) {
                val ni = i + k * di
                val nj = j + k * dj
                if (ni !in field.indices || nj !in field[ni].indices || field[ni][nj] != targetWord[k]) {
                    return false
                }
            }
            return true
        }

        field.forEachIndexed { i, row ->
            row.forEachIndexed { j, char ->
                if (char == 'X') {
                    val directions = listOf(
                        { isMatch(i, j, 0, 1) }, // right
                        { isMatch(i, j, 0, -1) },// left
                        { isMatch(i, j, -1, 0) },  // up
                        { isMatch(i, j, 1, 0) }, // down
                        { isMatch(i, j, 1, 1) }, // diagonal right down
                        { isMatch(i, j, -1, 1) }, // diagonal right up
                        { isMatch(i, j, 1, -1) }, // diagonal left down
                        { isMatch(i, j, -1, -1) }, // diagonal left up
                    )
                    result += directions.count { it() }
                }
            }
        }

        return result
    }

    override fun partTwo(): Int {

        var result = 0

        fun isXMasPattern(i: Int, j: Int) = field[i][j] == 'A'
                && ((field[i - 1][j - 1] == 'M' && field[i + 1][j + 1] == 'S')
                || (field[i - 1][j - 1] == 'S' && field[i + 1][j + 1] == 'M'))
                && ((field[i - 1][j + 1] == 'M' && field[i + 1][j - 1] == 'S')
                || (field[i - 1][j + 1] == 'S' && field[i + 1][j - 1] == 'M'))

        for (i in 1 until field.size - 1) {
            for (j in 1 until field[i].length - 1) {
                if (isXMasPattern(i, j)) {
                    result++
                }
            }
        }

        return result
    }
}
