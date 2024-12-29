package de.pgebert.aoc

import de.pgebert.aoc.days.Day21
import de.pgebert.aoc.utils.shouldBe
import org.junit.jupiter.api.Test


class Day21Test {

    private val example = """
    029A
    980A
    179A
    456A
    379A
    """.trimIndent()

    @Test
    fun `testing day 21 partOne example`() {
        val day = Day21(input = example)
        day.partOne() shouldBe 126384
    }

    @Test
    fun `testing day 21 partTwo example`() {
        val day = Day21(input = example)
        day.partTwo() shouldBe 3
    }

    @Test
    fun `testing day 21 partOne`() {
        val day = Day21()
        day.partOne() shouldBe 202648 // 207144 too high
    }

    @Test
    fun `testing day 21 partTwo`() {
        val day = Day21()
        day.partTwo() shouldBe 248919739734728
    }
}
