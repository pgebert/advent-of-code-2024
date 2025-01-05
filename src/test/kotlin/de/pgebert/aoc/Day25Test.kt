package de.pgebert.aoc

import de.pgebert.aoc.days.Day25
import de.pgebert.aoc.utils.shouldBe
import org.junit.jupiter.api.Test


class Day25Test {

    private val example = """
        #####
        .####
        .####
        .####
        .#.#.
        .#...
        .....

        #####
        ##.##
        .#.##
        ...##
        ...#.
        ...#.
        .....

        .....
        #....
        #....
        #...#
        #.#.#
        #.###
        #####

        .....
        .....
        #.#..
        ###..
        ###.#
        ###.#
        #####

        .....
        .....
        .....
        #....
        #.#..
        #.#.#
        #####
    """.trimIndent()

    @Test
    fun `testing day 25 partOne example`() {
        val day = Day25(input = example)
        day.partOne() shouldBe 3
    }
    
}
