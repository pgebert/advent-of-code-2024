package de.pgebert.aoc.days

import de.pgebert.aoc.Day

class Day09(input: String? = null) : Day(9, "Day9", input) {

    override fun partOne(): Long {

        val fls = readFileSystem()

        var i = 0
        var j = fls.size - 1

        while (i < j) {
            if (fls[i] == ".") {

                while (j >= i && fls[j] == ".") {
                    j--
                }

                if (j > i) {
                    fls[i] = fls[j]
                    fls[j] = "."
                }
            }
            i++
        }

        return checksum(fls)
    }

    override fun partTwo(): Long {

        val fls = readFileSystem()

        var j = fls.size - 1

        while (j >= 0) {
            var blockSize = 0
            while (j - blockSize >= 0 && fls[j - blockSize] == fls[j]) {
                blockSize++
            }

            var i = 0

            inner@ while (i < j - blockSize) {
                if (fls[i] == ".") {
                    var freeSize = 0
                    while (fls[i + freeSize] == ".") {
                        freeSize++
                    }
                    if (freeSize >= blockSize) {
                        for (k in 0 until blockSize) {
                            fls[i + k] = fls[j - k]
                            fls[j - k] = "."
                        }
                        break@inner
                    }
                }
                i += 1
            }
            j -= blockSize
        }

        return checksum(fls)
    }

    private fun readFileSystem() = buildList<String> {
        inputString.forEachIndexed { i, c ->
            for (j in 0 until c.digitToInt()) {
                when {
                    i % 2 == 0 -> add((i / 2).toString())
                    else -> add(".")
                }
            }
        }
    }.toMutableList()

    private fun checksum(fls: List<String>) = fls
        .mapIndexed { i, c -> i * if (c != ".") c.toLong() else 0 }
        .sumOf { it }
}
