package de.pgebert.aoc.days

import de.pgebert.aoc.Day

class Day08(input: String? = null) : Day(8, "Day8", input) {


    override fun partOne() = buildSet {
        getAntennaLocations().forEach { locations ->
            locations.combinations().forEach { (location1, location2) ->
                calculateAntinodeRecursively(location1, location2, false).firstOrNull()?.let { add(it) }
                calculateAntinodeRecursively(location2, location1, false).firstOrNull()?.let { add(it) }
            }
        }
    }.size

    override fun partTwo() = buildSet {
        getAntennaLocations().forEach { locations ->
            locations.combinations().forEach { (start, end) ->
                calculateAntinodeRecursively(start, end, true).forEach { add(it) }
                calculateAntinodeRecursively(end, start, true).forEach { add(it) }
            }
        }
    }.size


    private fun getAntennaLocations(): List<List<Pair<Int, Int>>> {
        val antennas = mutableMapOf<Char, MutableList<Pair<Int, Int>>>()

        inputList.forEachIndexed { i, row ->
            row.forEachIndexed { j, c ->
                if (c != '.') {
                    antennas.getOrPut(c) { mutableListOf() }.add(i to j)
                }
            }
        }

        return antennas.values.toList()
    }

    private fun Pair<Int, Int>.isWithinBounds() =
        first in inputList.indices && second in inputList.first().indices

    private fun calculateAntinodeRecursively(
        start: Pair<Int, Int>,
        end: Pair<Int, Int>,
        includeStart: Boolean
    ): Sequence<Pair<Int, Int>> = sequence {
        val offsetX = (end.first - start.first)
        val offsetY = (end.second - start.second)

        var current = start

        while (current.isWithinBounds()) {
            if (current != start || includeStart) {
                yield(current)
            }
            current = current.first - offsetX to current.second - offsetY
        }
    }

    private fun <T> List<T>.combinations(): Sequence<Pair<T, T>> = sequence {

        for (i in indices) {
            for (j in i + 1 until size) {
                yield(get(i) to get(j))
            }
        }
    }

}