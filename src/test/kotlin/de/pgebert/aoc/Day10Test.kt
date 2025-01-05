package de.pgebert.aoc

import de.pgebert.aoc.days.Day10
import de.pgebert.aoc.utils.shouldBe
import org.junit.jupiter.api.Test


class Day10Test {

    private val example = """
        89010123
        78121874
        87430965
        96549874
        45678903
        32019012
        01329801
        10456732
    """.trimIndent()

    @Test
    fun `testing day 02 partOne example`() {
        val day = Day10(input = example)
        day.partOne() shouldBe 36
    }

    @Test
    fun `testing day 02 partTwo example`() {
        val day = Day10(input = example)
        day.partTwo() shouldBe 81
    }
    
}
