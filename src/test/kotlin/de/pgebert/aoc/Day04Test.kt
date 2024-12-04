package de.pgebert.aoc

import de.pgebert.aoc.days.Day04
import de.pgebert.aoc.utils.shouldBe
import org.junit.jupiter.api.Test


class Day04Test {

    private val example = """
        MMMSXXMASM
        MSAMXMSMSA
        AMXSXMAAMM
        MSAMASMSMX
        XMASAMXAMM
        XXAMMXXAMA
        SMSMSASXSS
        SAXAMASAAA
        MAMMMXMMMM
        MXMXAXMASX
    """.trimIndent()

    @Test
    fun `testing day 02 partOne example`() {
        val day = Day04(input = example)
        day.partOne() shouldBe 18
    }

    @Test
    fun `testing day 02 partTwo example`() {
        val day = Day04(input = example)
        day.partTwo() shouldBe 9
    }
}
