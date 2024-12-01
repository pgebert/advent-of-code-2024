@file:Suppress("SpellCheckingInspection")

package de.pgebert.aoc

import de.pgebert.aoc.days.*
import de.pgebert.aoc.utils.shouldBe
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

class AllDaysTest {
    private data class Answer(
        val day: Day,
        val partOne: Any,
        val partTwo: Any
    )

    @TestFactory
    fun answers() = listOf(
        Answer(Day01(), NOT_IMPLEMENTED, NOT_IMPLEMENTED),
        Answer(Day02(), NOT_IMPLEMENTED, NOT_IMPLEMENTED),
        Answer(Day03(), NOT_IMPLEMENTED, NOT_IMPLEMENTED),
        Answer(Day04(), NOT_IMPLEMENTED, NOT_IMPLEMENTED),
        Answer(Day05(), NOT_IMPLEMENTED, NOT_IMPLEMENTED),
        Answer(Day06(), NOT_IMPLEMENTED, NOT_IMPLEMENTED),
        Answer(Day07(), NOT_IMPLEMENTED, NOT_IMPLEMENTED),
        Answer(Day08(), NOT_IMPLEMENTED, NOT_IMPLEMENTED),
        Answer(Day09(), NOT_IMPLEMENTED, NOT_IMPLEMENTED),
        Answer(Day10(), NOT_IMPLEMENTED, NOT_IMPLEMENTED),
        Answer(Day11(), NOT_IMPLEMENTED, NOT_IMPLEMENTED),
        Answer(Day12(), NOT_IMPLEMENTED, NOT_IMPLEMENTED),
        Answer(Day13(), NOT_IMPLEMENTED, NOT_IMPLEMENTED),
        Answer(Day14(), NOT_IMPLEMENTED, NOT_IMPLEMENTED),
        Answer(Day15(), NOT_IMPLEMENTED, NOT_IMPLEMENTED),
        Answer(Day16(), NOT_IMPLEMENTED, NOT_IMPLEMENTED),
        Answer(Day17(), NOT_IMPLEMENTED, NOT_IMPLEMENTED),
        Answer(Day18(), NOT_IMPLEMENTED, NOT_IMPLEMENTED),
        Answer(Day19(), NOT_IMPLEMENTED, NOT_IMPLEMENTED),
        Answer(Day20(), NOT_IMPLEMENTED, NOT_IMPLEMENTED),
        Answer(Day21(), NOT_IMPLEMENTED, NOT_IMPLEMENTED),
        Answer(Day22(), NOT_IMPLEMENTED, NOT_IMPLEMENTED),
        Answer(Day23(), NOT_IMPLEMENTED, NOT_IMPLEMENTED),
        Answer(Day24(), NOT_IMPLEMENTED, NOT_IMPLEMENTED),
        Answer(Day25(), NOT_IMPLEMENTED, NOT_IMPLEMENTED),
    ).map { (day, expectedPartOne, expectedPartTwo) ->

        DynamicTest.dynamicTest("Day ${day.number} - ${day.title}") {
            print("Testing Part 1 - Expecting $expectedPartOne..")
            day.partOne() shouldBe expectedPartOne
            print(" SUCCESS\n")

            print("Testing Part 2 - Expecting $expectedPartTwo..")
            day.partTwo() shouldBe expectedPartTwo
            print(" SUCCESS\n")
        }

    }
}
