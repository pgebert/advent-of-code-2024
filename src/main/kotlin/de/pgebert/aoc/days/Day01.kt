package de.pgebert.aoc.days

import de.pgebert.aoc.Day
import de.pgebert.aoc.InputReader
import de.pgebert.aoc.NOT_IMPLEMENTED
import de.pgebert.aoc.utils.toInputList
import kotlin.math.abs

class Day01(input: String? = null) : Day(1, "Day1", input) {

    override fun partOne() : Int {

        val (list1, list2) = parseInput()

        return list1.sorted().zip(list2.sorted()).sumOf { (a, b)  ->
            abs(a-b)
        }
    }

    override fun partTwo() : Int {

        val (list1, list2) = parseInput()

        return list1.sumOf { a ->
            a * list2.count { it == a }
        }
    }

    private fun parseInput(): Pair<MutableList<Int>, MutableList<Int>> {
        val list1 = mutableListOf<Int>()
        val list2 = mutableListOf<Int>()

        inputList.forEach {
            val (a, b) = it.split(" ").filterNot { it.isBlank() }
            list1.add(a.toInt())
            list2.add(b.toInt())
        }
        return Pair(list1, list2)
    }
}
