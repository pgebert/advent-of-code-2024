package de.pgebert.aoc

import de.pgebert.aoc.days.Day13
import de.pgebert.aoc.utils.shouldBe
import org.junit.jupiter.api.Test


class Day13Test {

    private val example = """
        Button A: X+94, Y+34
        Button B: X+22, Y+67
        Prize: X=8400, Y=5400
        
        Button A: X+26, Y+66
        Button B: X+67, Y+21
        Prize: X=12748, Y=12176
        
        Button A: X+17, Y+86
        Button B: X+84, Y+37
        Prize: X=7870, Y=6450
        
        Button A: X+69, Y+23
        Button B: X+27, Y+71
        Prize: X=18641, Y=10279
    """.trimIndent()

    @Test
    fun `testing day 02 partOne example`() {
        val day = Day13(input = example)
        day.partOne() shouldBe 480
    }

    @Test
    fun `testing day 02 partTwo example`() {
        val day = Day13(input = example)
        day.partTwo() shouldBe 875318608908
    }


    @Test
    fun `testing day 02 partOne`() {
        val day = Day13()
        day.partOne() shouldBe 32026
    }

    @Test
    fun `testing day 02 partTwo`() {
        val day = Day13()
        day.partTwo() shouldBe 89013607072065
    }
}
