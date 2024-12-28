package de.pgebert.aoc.days

import de.pgebert.aoc.Day
import de.pgebert.aoc.NOT_IMPLEMENTED

class Day25(input: String? = null) : Day(25, "Day25", input) {

    override fun partOne(): Int {

        val (locks, keys) = getLocksAndKeys()

        var count = 0

        locks.forEach { lock ->
            keys.forEach { key ->
                if (lock.zip(key).all { (l, k) -> l + k <= 5 }) {
                    count++
                }
            }
        }

        return count
    }

    private fun getLocksAndKeys(): Pair<List<List<Int>>, List<List<Int>>> {

        val locks = mutableListOf<List<Int>>()
        val keys = mutableListOf<List<Int>>()

        inputList.filterNot { it.isBlank() }.windowed(7, 7).forEach { window ->
            if (window.first() == "#####") {
                locks.add(
                    (0 until 5).map { i ->
                        window.indexOfFirst { line -> line[i] == '.' } - 1
                    }
                )
            } else {
                keys.add(
                    (0 until 5).map { i ->
                        window.reversed().indexOfFirst { line -> line[i] == '.' } - 1
                    }
                )
            }
        }

        return locks to keys
    }

    override fun partTwo() = NOT_IMPLEMENTED
}
