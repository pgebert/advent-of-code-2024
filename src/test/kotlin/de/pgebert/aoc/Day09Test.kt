package de.pgebert.aoc

import de.pgebert.aoc.days.Day09
import de.pgebert.aoc.utils.shouldBe
import org.junit.jupiter.api.Test


class Day09Test {

    private val example = "2333133121414131402"

    @Test
    fun `testing day 02 partOne example`() {
        val day = Day09(input = example)
        day.partOne() shouldBe 1928
    }

    @Test
    fun `testing day 02 partTwo example`() {
        val day = Day09(input = example)
        day.partTwo() shouldBe 2858
    }


    @Test
    fun `testing day 02 partOne`() {
        val day = Day09()
        day.partOne() shouldBe 6435922584968
    }

    @Test
    fun `testing day 02 partTwo`() {
        val day = Day09()
        day.partTwo() shouldBe 6469636832766
    }
}
