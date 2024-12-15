package de.pgebert.aoc.days

import de.pgebert.aoc.Day
import kotlin.math.max
import kotlin.math.min


open class Point(var x: ClosedRange<Int>, var y: ClosedRange<Int>)

class Robot(x: ClosedRange<Int>, y: ClosedRange<Int>) : Point(x, y)
class Package(x: ClosedRange<Int>, y: ClosedRange<Int>) : Point(x, y)
class Wall(x: ClosedRange<Int>, y: ClosedRange<Int>) : Point(x, y)

class Day15(input: String? = null) : Day(15, "Day15", input) {

    override fun partOne(): Int {

        val field = inputList.filter { it.startsWith("#") }
        val commands = inputList.filter { !it.startsWith("#") && it.isNotBlank() }.joinToString("")

        val board = buildList {
            for (i in field.indices) {
                for (j in field[i].indices) {
                    when (field[i][j]) {
                        '@' -> add(Robot(i..i, j..j))
                        'O' -> add(Package(i..i, j..j))
                        '#' -> add(Wall(i..i, j..j))
                    }
                }
            }
        }

        commands.forEach { command ->
            val direction = directionByCommand[command]!!
            val robot = board.first { it is Robot }

            move(robot, direction, board)
        }

        return board.filter { it is Package }.sumOf { it.x.start * 100 + it.y.start }
    }

    override fun partTwo(): Int {

        val field = inputList.filter { it.startsWith("#") }
        val commands = inputList.filter { !it.startsWith("#") && it.isNotBlank() }.joinToString("")

        val board = buildList {
            for (i in field.indices) {
                for (j in field[i].indices) {
                    when (field[i][j]) {
                        '@' -> add(Robot(i..i, j * 2..j * 2))
                        'O' -> add(Package(i..i, j * 2..j * 2 + 1))
                        '#' -> add(Wall(i..i, j * 2..j * 2 + 1))
                    }
                }
            }
        }

        commands.forEach { command ->
            val direction = directionByCommand[command]!!
            val robot = board.first { it is Robot }

            move(robot, direction, board)
        }

        return board.filter { it is Package }.sumOf { it.x.start * 100 + it.y.start }
    }

    private fun List<Point>.evolveInDirection(dir: Pair<Int, Int>, board: List<Point>): Set<Point> = flatMap { pos ->
        val newPos = Point(pos.x.plus(dir.first), pos.y.plus(dir.second))
        val existing = board.filter { it.x.containedIn(newPos.x) > 0 && it.y.containedIn(newPos.y) > 0 }
        setOf(pos) + existing.evolveInDirection(dir, board - existing)
    }.toSet()

    private fun move(
        pos: Point,
        dir: Pair<Int, Int>,
        board: List<Point>
    ): Boolean {
        val existing = listOf(pos).evolveInDirection(dir, board)
        if (existing.none { it is Wall }) {
            existing.forEach { point ->
                point.x = point.x.plus(dir.first)
                point.y = point.y.plus(dir.second)
            }
            return true
        }
        return false
    }

    val directionByCommand = mapOf(
        '^' to Pair(-1, 0),  // up
        '>' to Pair(0, 1),   // right
        'v' to Pair(1, 0),   // down
        '<' to Pair(0, -1)   // left
    )

    fun ClosedRange<Int>.plus(i: Int) = start + i..endInclusive + i

    fun ClosedRange<Int>.containedIn(other: ClosedRange<Int>) =
        (min(endInclusive, other.endInclusive) - max(start, other.start)).plus(1)
            .coerceAtLeast(0)
}
