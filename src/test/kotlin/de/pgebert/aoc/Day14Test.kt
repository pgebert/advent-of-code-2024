package de.pgebert.aoc

import de.pgebert.aoc.days.Day14
import de.pgebert.aoc.utils.shouldBe
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

//    @Test
//    fun `testing day 14 partOne example`() {
//        val day = Day14(input = example)
//        day.partOne() shouldBe 12
//    }
//
//    @Test
//    fun `testing day 14 partTwo example`() {
//        val day = Day14(input = example)
//        day.partTwo() shouldBe 875318608908
//    }

    @Test
    fun `testing day 14 partOne`() {
        val day = Day14()
        day.partOne() shouldBe 229839456
    }

    @Test
    fun `testing day 14 partTwo`() {
        val day = Day14()
        day.partTwo() shouldBe 7138
    }
}
