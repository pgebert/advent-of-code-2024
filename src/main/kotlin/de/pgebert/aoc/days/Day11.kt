package de.pgebert.aoc.days

import de.pgebert.aoc.Day

class Day11(input: String? = null) : Day(11, "Day11", input) {

    override fun partOne() = processStones(25)
    
    override fun partTwo() = processStones(75)

    fun processStones(times: Int): Long {
        var stones = inputString.split(" ")
            .filterNot { it.isBlank() }
            .groupingBy { it }
            .eachCount()
            .mapValues { (_, v) -> v.toLong() }

        repeat(times) {

            val newStones = mutableMapOf<String, Long>()

            stones.forEach { (stone, count) ->
                when {
                    stone == "0" -> {
                        newStones["1"] = newStones.getOrDefault("1", 0L) + count
                    }

                    stone.length % 2 == 0 -> {
                        val (first, second) = stone.splitInHalf()
                        newStones[first] = newStones.getOrDefault(first, 0L) + count
                        newStones[second] = newStones.getOrDefault(second, 0L) + count
                    }

                    else -> {
                        val multiplied = (stone.toLong() * 2024).toString()
                        newStones[multiplied] = newStones.getOrDefault(multiplied, 0L) + count
                    }
                }
            }

            stones = newStones
        }

        return stones.values.sum()
    }

    private fun String.splitInHalf(): Pair<String, String> {
        val mid = length / 2
        val first = this.substring(0, mid).removeLeadingZeros()
        val second = this.substring(mid).removeLeadingZeros()
        return first to second
    }

    private fun String.removeLeadingZeros() =
        trimStart('0').ifEmpty { "0" }
}
