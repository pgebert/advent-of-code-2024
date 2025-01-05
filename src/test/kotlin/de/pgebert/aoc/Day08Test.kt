package de.pgebert.aoc

import de.pgebert.aoc.days.Day08
import de.pgebert.aoc.utils.shouldBe
import org.junit.jupiter.api.Test


class Day08Test {

    private val example = """
        ............
        ........0...
        .....0......
        .......0....
        ....0.......
        ......A.....
        ............
        ............
        ........A...
        .........A..
        ............
        ............
    """.trimIndent()

    @Test
    fun `testing day 02 partOne example`() {
        val day = Day08(input = example)
        day.partOne() shouldBe 14
    }

    @Test
    fun `testing day 02 partTwo example`() {
        val day = Day08(input = example)
        day.partTwo() shouldBe 34
    }
    
}
