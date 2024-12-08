package de.pgebert.aoc

import de.pgebert.aoc.days.Day07
import de.pgebert.aoc.utils.shouldBe
import org.junit.jupiter.api.Test


class Day07Test {

    private val example = """
        190: 10 19
        3267: 81 40 27
        83: 17 5
        156: 15 6
        7290: 6 8 6 15
        161011: 16 10 13
        192: 17 8 14
        21037: 9 7 18 13
        292: 11 6 16 20
    """.trimIndent()

    @Test
    fun `testing day 02 partOne example`() {
        val day = Day07(input = example)
        day.partOne() shouldBe 3749
    }

    @Test
    fun `testing day 02 partTwo example`() {
        val day = Day07(input = example)
        day.partTwo() shouldBe 11387
    }
}
