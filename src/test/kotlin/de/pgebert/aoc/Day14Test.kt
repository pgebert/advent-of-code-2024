package de.pgebert.aoc

import de.pgebert.aoc.days.Day14
import de.pgebert.aoc.utils.shouldBe
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test


class Day14Test {

    private val example = """
        p=0,4 v=3,-3
        p=6,3 v=-1,-3
        p=10,3 v=-1,2
        p=2,0 v=2,-1
        p=0,0 v=1,3
        p=3,0 v=-2,-2
        p=7,6 v=-1,-3
        p=3,0 v=-1,-2
        p=9,3 v=2,3
        p=7,3 v=-1,2
        p=2,4 v=2,-3
        p=9,5 v=-3,-3
    """.trimIndent()

    @Test
    fun `testing day 14 partOne example`() {
        val day = Day14(input = example)
        day.partOne(11, 7) shouldBe 12
    }

    @Test
    @Disabled
    fun `testing day 14 partTwo example`() {
        val day = Day14(input = example)
        day.partTwo(11, 7) shouldBe 875318608908
    }

}
