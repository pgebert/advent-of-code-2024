package de.pgebert.aoc.days

import de.pgebert.aoc.Day

class Day14(input: String? = null) : Day(14, "Day14", input) {


    override fun partOne() = partOne(boardWidth = 101, boardHeight = 103)

    fun partOne(boardWidth: Int, boardHeight: Int): Int {

        val rounds = 100

        val robots = getRobots()
        repeat(rounds) {
            robots.forEach { robot ->
                robot.position = robot.position.plus(robot.velocity).modulo(boardHeight to boardWidth)
            }
        }

        val countsByQuarter = mutableMapOf<Int, Int>(
            0 to 0,
            1 to 0,
            2 to 0,
            3 to 0,
        )

        robots.forEach { robot ->
            when {
                robot.position.first < boardHeight / 2 && robot.position.second < boardWidth / 2 -> {
                    countsByQuarter[0] = countsByQuarter[0]!! + 1
                }

                robot.position.first < boardHeight / 2 && robot.position.second > boardWidth / 2 -> {
                    countsByQuarter[1] = countsByQuarter[1]!! + 1
                }

                robot.position.first > boardHeight / 2 && robot.position.second < boardWidth / 2 -> {
                    countsByQuarter[2] = countsByQuarter[2]!! + 1
                }

                robot.position.first > boardHeight / 2 && robot.position.second > boardWidth / 2 -> {
                    countsByQuarter[3] = countsByQuarter[3]!! + 1
                }
            }
        }

        return countsByQuarter.values.reduce { acc, i -> acc * i }
    }

    override fun partTwo() = partTwo(boardWidth = 101, boardHeight = 103)

    fun partTwo(boardWidth: Int, boardHeight: Int): Int {

        val regionThreshold = 229

        val robots = getRobots()
        var round = 0

        while (true) {
            round++

            robots.forEach { robot ->
                robot.position = robot.position.plus(robot.velocity).modulo(boardHeight to boardWidth)
            }

            val maxRegionSize = getMaxRegionSize(robots.map { it.position }.toSet())

            if (maxRegionSize >= regionThreshold) {
                return round
            }
        }

        return 0
    }

    fun getMaxRegionSize(robots: Set<Pair<Int, Int>>): Int {
        var maxSize = 0

        val visited = mutableSetOf<Pair<Int, Int>>()

        robots.forEach { position ->
            if (position !in visited) {
                val regionSize = expandRegion(position, robots, visited)
                if (regionSize > maxSize) {
                    maxSize = regionSize
                }
            }
        }

        return maxSize
    }

    private fun expandRegion(
        position: Pair<Int, Int>,
        robots: Set<Pair<Int, Int>>,
        visited: MutableSet<Pair<Int, Int>>
    ): Int {

        var regionSize = 1
        visited.add(position)

        for (x in -1..1) {
            for (y in -1..1) {
                val newPosition = Pair(position.first + x, position.second + y)
                if (newPosition in robots && newPosition !in visited) {
                    regionSize += expandRegion(Pair(position.first + x, position.second + y), robots, visited)
                }
            }
        }

        return regionSize

    }

    private fun Pair<Int, Int>.plus(other: Pair<Int, Int>) = first + other.first to second + other.second
    private fun Pair<Int, Int>.modulo(other: Pair<Int, Int>) =
        (first + other.first) % other.first to (second + other.second) % other.second

    data class Robot(
        var position: Pair<Int, Int>,
        var velocity: Pair<Int, Int>,
    )

    private fun getRobots() = buildList() {
        inputList.forEach { line ->
            val (posY, posX, velY, velX) = Regex("p=(\\d+),(\\d+) v=(-?\\d+),(-?\\d+)").matchEntire(line)!!.destructured
            add(Robot(position = posX.toInt() to posY.toInt(), velocity = velX.toInt() to velY.toInt()))
        }
    }
}
