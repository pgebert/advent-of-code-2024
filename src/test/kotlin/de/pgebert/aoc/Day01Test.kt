package de.pgebert.aoc

import de.pgebert.aoc.days.Day01
import de.pgebert.aoc.utils.shouldBe
import org.junit.jupiter.api.Test


class Day01Test {

    private val example = """
            3   4
            4   3
            2   5
            1   3
            3   9
            3   3
        """

    @Test
    fun `testing day 01 partOne example`() {
        val day = Day01(input = example)
        day.partOne() shouldBe 11
    }

    @Test
    fun `testing day 01 partTwo example`() {
        val day = Day01(input = example)
        day.partTwo() shouldBe 31
    }
}
