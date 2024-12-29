package de.pgebert.aoc

import de.pgebert.aoc.days.Day16
import de.pgebert.aoc.utils.shouldBe
import org.junit.jupiter.api.Test


class Day16Test {

    private val example = """
        ###############
        #.......#....E#
        #.#.###.#.###.#
        #.....#.#...#.#
        #.###.#####.#.#
        #.#.#.......#.#
        #.#.#####.###.#
        #...........#.#
        ###.#.#####.#.#
        #...#.....#.#.#
        #.#.#.###.#.#.#
        #.....#...#.#.#
        #.###.#.#.#.#.#
        #S..#.....#...#
        ###############
    """.trimIndent()

    @Test
    fun `testing day 16 partOne example`() {
        val day = Day16(input = example)
        day.partOne() shouldBe 7036
    }

    @Test
    fun `testing day 16 partTwo example`() {
        val day = Day16(input = example)
        day.partTwo() shouldBe 45
    }

    @Test
    fun `testing day 16 partOne`() {
        val day = Day16()
        day.partOne() shouldBe 111480
    }

    @Test
    fun `testing day 16 partTwo`() {
        val day = Day16()
        day.partTwo() shouldBe 529
    }
}
