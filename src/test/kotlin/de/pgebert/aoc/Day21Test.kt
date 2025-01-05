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
    
}
