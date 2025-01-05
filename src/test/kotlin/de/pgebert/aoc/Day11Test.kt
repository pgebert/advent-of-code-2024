package de.pgebert.aoc

import de.pgebert.aoc.days.Day11
import de.pgebert.aoc.utils.shouldBe
import org.junit.jupiter.api.Test


class Day11Test {

    private val example = "125 17"

    @Test
    fun `testing day 02 partOne example`() {
        val day = Day11(input = example)
        day.partOne() shouldBe 55312
    }

    @Test
    fun `testing day 02 partTwo example`() {
        val day = Day11(input = example)
        day.partTwo() shouldBe 65601038650482
    }
    
}
