package de.pgebert.aoc

import de.pgebert.aoc.days.Day17
import de.pgebert.aoc.utils.shouldBe
import org.junit.jupiter.api.Test


class Day17Test {

    private val example = """
        Register A: 729
        Register B: 0
        Register C: 0

        Program: 0,1,5,4,3,0
    """.trimIndent()

    @Test
    fun `testing day 17 partOne example`() {
        val day = Day17(input = example)
        day.partOne() shouldBe "4,6,3,5,6,3,5,2,1,0"
    }

    @Test
    fun `testing day 17 partTwo example`() {
        val day = Day17(input = example)
        day.partTwo() shouldBe 202367025818154
    }

}
