package de.pgebert.aoc

import de.pgebert.aoc.days.Day12
import de.pgebert.aoc.utils.shouldBe
import org.junit.jupiter.api.Test


class Day12Test {

    private val example = """
        AAAA
        BBCD
        BBCC
        EEEC
    """.trimIndent()

    @Test
    fun `testing day 02 partOne example`() {
        val day = Day12(input = example)
        day.partOne() shouldBe 140
    }

    @Test
    fun `testing day 02 partTwo example`() {
        val day = Day12(input = example)
        day.partTwo() shouldBe 80
    }
    
}
