package de.pgebert.aoc

import de.pgebert.aoc.days.Day23
import de.pgebert.aoc.utils.shouldBe
import org.junit.jupiter.api.Test


class Day23Test {

    private val example = """
        kh-tc
        qp-kh
        de-cg
        ka-co
        yn-aq
        qp-ub
        cg-tb
        vc-aq
        tb-ka
        wh-tc
        yn-cg
        kh-ub
        ta-co
        de-co
        tc-td
        tb-wq
        wh-td
        ta-ka
        td-qp
        aq-cg
        wq-ub
        ub-vc
        de-ta
        wq-aq
        wq-vc
        wh-yn
        ka-de
        kh-ta
        co-tc
        wh-qp
        tb-vc
        td-yn
    """.trimIndent()

    @Test
    fun `testing day 23 partOne example`() {
        val day = Day23(input = example)
        day.partOne() shouldBe 7
    }

    @Test
    fun `testing day 23 partTwo example`() {
        val day = Day23(input = example)
        day.partTwo() shouldBe "co,de,ka,ta"
    }

    @Test
    fun `testing day 23 partOne`() {
        val day = Day23()
        day.partOne() shouldBe 1248
    }

    @Test
    fun `testing day 23 partTwo`() {
        val day = Day23()
        day.partTwo() shouldBe "aa,cf,cj,cv,dr,gj,iu,jh,oy,qr,xr,xy,zb"
    }
}
