package de.pgebert.aoc

import de.pgebert.aoc.days.Day02
import de.pgebert.aoc.utils.shouldBe
import org.junit.jupiter.api.Test


class Day02Test {

    private val example = """
            7 6 4 2 1
            1 2 7 8 9
            9 7 6 2 1
            1 3 2 4 5
            8 6 4 4 1
            1 3 6 7 9
        """

    @Test
    fun `testing day 02 partOne example`() {
        val day = Day02(input = example)
        day.partOne() shouldBe 2
    }

    @Test
    fun `testing day 02 partTwo example`() {
        val day = Day02(input = example)
        day.partTwo() shouldBe 4
    }
}
