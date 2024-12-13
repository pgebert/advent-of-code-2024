package de.pgebert.aoc.days

import de.pgebert.aoc.Day

class Day13(input: String? = null) : Day(13, "Day13", input) {

    override fun partOne() = getGames().estimateCosts()

    override fun partTwo() = getGames().estimateCosts(addToPrize = 10_000_000_000_000)


    private fun List<Game>.estimateCosts(addToPrize: Long = 0L): Long {
        var result = 0L

        forEach { game ->
            val (prizeX, prizeY) = game.prize.plus(addToPrize)

            val (offsetAX, offsetAY) = game.offsetA
            val (offsetBX, offsetBY) = game.offsetB

            val a = 1.0 * (prizeX * offsetBY - offsetBX * prizeY) / (offsetBY * offsetAX - offsetAY * offsetBX)
            val b = (prizeY - a * offsetAY) / offsetBY

            if (a.toLong() * offsetAX + b.toLong() * offsetBX == prizeX &&
                a.toLong() * offsetAY + b.toLong() * offsetBY == prizeY
            ) {
                result += a.toLong() * 3 + b.toLong()
            }
        }

        return result
    }

    private fun Pair<Int, Int>.plus(a: Long) = Pair(first + a, second + a)


    data class Game(
        val offsetA: Pair<Int, Int>,
        val offsetB: Pair<Int, Int>,
        val prize: Pair<Int, Int>,
    )

    private fun getGames(): List<Game> {

        val games = mutableListOf<Game>()

        var offsetA: Pair<Int, Int>? = null
        var offsetB: Pair<Int, Int>? = null
        var prize: Pair<Int, Int>? = null

        inputList.forEach { line ->
            when {
                line.startsWith("Button A") -> offsetA = line.getAllDigits().let { it.first() to it.last() }
                line.startsWith("Button B") -> offsetB = line.getAllDigits().let { it.first() to it.last() }
                line.startsWith("Prize") -> {
                    prize = line.getAllDigits().let { it.first() to it.last() }
                    games.add(Game(offsetA!!, offsetB!!, prize))
                }

                else -> {}
            }
        }

        return games

    }

    private fun String.getAllDigits() = Regex("\\d+").findAll(this).map { it.value.toInt() }.toList()
}
