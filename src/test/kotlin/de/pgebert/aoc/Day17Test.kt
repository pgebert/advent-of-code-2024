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

//    private val example = """
//        Register A: 203200887004610
//        Register B: 0
//        Register C: 0
//
//        Program: 2,4,1,1,7,5,4,7,1,4,0,3,5,5,3,0
//    """.trimIndent()

    @Test
    fun `testing day 17 partOne example`() {
        val day = Day17(input = example)
//        day.partOne() shouldBe "2,4,1,1,7,5,4,7,1,4,0,3,5,5,3,0"
        day.partOne() shouldBe "4,6,3,5,6,3,5,2,1,0"
    }

    @Test
    fun `testing day 17 partTwo example`() {
        val day = Day17(input = example)
        day.partTwo() shouldBe 45 //117440 too low
    }

    @Test
    fun `testing day 17 partOne`() {
        val day = Day17()
        day.partOne() shouldBe "1,3,7,4,6,4,2,3,5"
    }

    @Test
    fun `testing day 17 partTwo`() {
        val day = Day17()
        day.partTwo() shouldBe 202367025818154 // 202367025599018 too low
    }
}
