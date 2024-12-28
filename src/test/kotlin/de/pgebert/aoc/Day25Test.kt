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

    @Test
    fun `testing day 25 partTwo example`() {
        val day = Day25(input = example)
        day.partTwo() shouldBe "co,de,ka,ta"
    }

    @Test
    fun `testing day 25 partOne`() {
        val day = Day25()
        day.partOne() shouldBe 3356
    }

    @Test
    fun `testing day 25 partTwo`() {
        val day = Day25()
        day.partTwo() shouldBe "aa,cf,cj,cv,dr,gj,iu,jh,oy,qr,xr,xy,zb"
    }
}
