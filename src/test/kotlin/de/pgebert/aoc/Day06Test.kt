package de.pgebert.aoc

import de.pgebert.aoc.days.Day06
import de.pgebert.aoc.utils.shouldBe
import org.junit.jupiter.api.Test


class Day06Test {

    private val example = """
        ....#.....
        .........#
        ..........
        ..#.......
        .......#..
        ..........
        .#..^.....
        ........#.
        #.........
        ......#...
    """.trimIndent()

    @Test
    fun `testing day 02 partOne example`() {
        val day = Day06(input = example)
        day.partOne() shouldBe 41
    }

    @Test
    fun `testing day 02 partTwo example`() {
        val day = Day06(input = example)
        day.partTwo() shouldBe 6
    }


    @Test
    fun `testing day 02 partTwo`() {
        val day = Day06()
        day.partTwo() shouldBe 1753
    }
}
