package de.pgebert.aoc

import de.pgebert.aoc.days.Day03
import de.pgebert.aoc.utils.shouldBe
import org.junit.jupiter.api.Test


class Day03Test {


    @Test
    fun `testing day 02 partOne example`() {
        val example = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))"
        val day = Day03(input = example)
        day.partOne() shouldBe 161
    }

    @Test
    fun `testing day 02 partTwo example`() {
        val example = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"
        val day = Day03(input = example)
        day.partTwo() shouldBe 48
    }
}
