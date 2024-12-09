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
        Answer(Day01(), 1110981, 24869388),
        Answer(Day02(), 432, 488),
        Answer(Day03(), 153469856, 77055967),
        Answer(Day04(), 2551, 1985),
        Answer(Day05(), 4766, 6257),
        Answer(Day06(), 5239, 1753),
        Answer(Day07(), 2664460013123, 426214131924213),
        Answer(Day08(), 247, 861),
        Answer(Day09(), 6435922584968, 6469636832766),
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
