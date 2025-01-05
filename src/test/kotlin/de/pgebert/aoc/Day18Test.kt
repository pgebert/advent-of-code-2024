package de.pgebert.aoc

import de.pgebert.aoc.days.Day18
import de.pgebert.aoc.utils.shouldBe
import org.junit.jupiter.api.Test


class Day18Test {

    private val example = """
        5,4
        4,2
        4,5
        3,0
        2,1
        6,3
        2,4
        1,5
        0,6
        3,3
        2,6
        5,1
        1,2
        5,5
        2,5
        6,5
        1,4
        0,4
        6,4
        1,1
        6,1
        1,0
        0,5
        1,6
        2,0
    """.trimIndent()

    @Test
    fun `testing day 18 partOne example`() {
        val day = Day18(input = example)
        day.partOne(7, 12) shouldBe 22
    }

    @Test
    fun `testing day 18 partTwo example`() {
        val day = Day18(input = example)
        day.partTwo(7, 12) shouldBe "6,1"
    }
    
}
