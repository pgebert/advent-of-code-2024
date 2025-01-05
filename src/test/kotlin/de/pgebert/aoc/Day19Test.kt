package de.pgebert.aoc

import de.pgebert.aoc.days.Day19
import de.pgebert.aoc.utils.shouldBe
import org.junit.jupiter.api.Test


class Day19Test {

    private val example = """
        r, wr, b, g, bwu, rb, gb, br

        brwrr
        bggr
        gbbr
        rrbgbr
        ubwu
        bwurrg
        brgr
        bbrgwb
    """.trimIndent()

    @Test
    fun `testing day 19 partOne example`() {
        val day = Day19(input = example)
        day.partOne() shouldBe 6
    }

    @Test
    fun `testing day 19 partTwo example`() {
        val day = Day19(input = example)
        day.partTwo() shouldBe 16
    }

}
