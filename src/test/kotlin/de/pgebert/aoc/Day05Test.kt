package de.pgebert.aoc

import de.pgebert.aoc.days.Day05
import de.pgebert.aoc.utils.shouldBe
import org.junit.jupiter.api.Test


class Day05Test {

    private val example = """
        47|53
        97|13
        97|61
        97|47
        75|29
        61|13
        75|53
        29|13
        97|29
        53|29
        61|53
        97|53
        61|29
        47|13
        75|47
        97|75
        47|61
        75|61
        47|29
        75|13
        53|13
        
        75,47,61,53,29
        97,61,53,29,13
        75,29,13
        75,97,47,61,53
        61,13,29
        97,13,75,29,47
    """.trimIndent()

    @Test
    fun `testing day 02 partOne example`() {
        val day = Day05(input = example)
        day.partOne() shouldBe 143
    }

    @Test
    fun `testing day 02 partTwo example`() {
        val day = Day05(input = example)
        day.partTwo() shouldBe 123
    }
}
