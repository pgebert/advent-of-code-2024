package de.pgebert.aoc

import de.pgebert.aoc.days.Day20
import de.pgebert.aoc.utils.shouldBe
import org.junit.jupiter.api.Test


class Day20Test {

    private val example = """
        ###############
        #...#...#.....#
        #.#.#.#.#.###.#
        #S#...#.#.#...#
        #######.#.#.###
        #######.#.#...#
        #######.#.###.#
        ###..E#...#...#
        ###.#######.###
        #...###...#...#
        #.#####.#.###.#
        #.#...#.#.#...#
        #.#.#.#.#.#.###
        #...#...#...###
        ###############
    """.trimIndent()

    @Test
    fun `testing day 20 partOne example`() {
        val day = Day20(input = example)
        day.partOne(minSaving = 64) shouldBe 1
    }

    @Test
    fun `testing day 20 partTwo example`() {
        val day = Day20(input = example)
        day.partTwo(minSaving = 76) shouldBe 3
    }
    
}
