package de.pgebert.aoc

import de.pgebert.aoc.days.Day22
import de.pgebert.aoc.utils.shouldBe
import org.junit.jupiter.api.Test


class Day22Test {

    private val example1 = """
        1
        10
        100
        2024
    """.trimIndent()

    private val example2 = """
        1
        2
        3
        2024
    """.trimIndent()

    @Test
    fun `testing day 22 partOne example`() {
        val day = Day22(input = example1)
        day.partOne() shouldBe 37327623
    }

    @Test
    fun `testing day 22 partTwo example`() {
        val day = Day22(input = example2)
        day.partTwo() shouldBe 23
    }
}
