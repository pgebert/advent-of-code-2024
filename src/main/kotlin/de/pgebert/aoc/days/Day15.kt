package de.pgebert.aoc.days

import de.pgebert.aoc.Day
import de.pgebert.aoc.NOT_IMPLEMENTED


open class Point(var x: Int, var y: Int)

class Robot(x: Int, y: Int) : Point(x, y)
class Package(x: Int, y: Int) : Point(x, y)
class Wall(x: Int, y: Int) : Point(x, y)

class Day15(input: String? = null) : Day(15, "Day15", input) {

    override fun partOne(): Int {

        val field = inputList.filter { it.startsWith("#") }
        val commands = inputList.filter { !it.startsWith("#") && it.isNotBlank() }.joinToString("")

        val board = buildList {

            for (i in field.indices) {
                for (j in field[i].indices) {
                    when (field[i][j]) {
                        '@' -> add(Robot(i, j))
                        'O' -> add(Package(i, j))
                        '#' -> add(Wall(i, j))
                    }
                }
            }
        }

        // print initial
//        println("Initial state:")
//        printBoard(field, board)

        commands.forEach { command ->

            val direction = directionByCommand[command]!!
            val robot = board.first { it is Robot }

            move(robot, direction, board)


            // print field
//            println("Move: $command")
//            printBoard(field, board)
        }

        return board.filter { it is Package }.sumOf { it.x * 100 + it.y }
    }

    private fun printBoard(
        field: List<String>,
        board: List<Point>
    ) {
        for (i in field.indices) {
            for (j in field[i].indices) {

                val pos = board.firstOrNull { it.x == i && it.y == j }
                when (pos) {
                    is Robot -> print("@")
                    is Wall -> print("#")
                    is Package -> print("O")
                    else -> print(".")
                }
            }
            print("\n")
        }
        print("\n")
    }

    private fun move(
        pos: Point,
        dir: Point,
        board: List<Point>
    ): Boolean {
        var newPos = board.firstOrNull { it.x == pos.x + dir.x && it.y == pos.y + dir.y }
        when (newPos) {
            is Wall -> return false
            is Package -> {
                val success = move(newPos, dir, board)
                if (success) {
                    pos.x = pos.x + dir.x
                    pos.y = pos.y + dir.y
                }
                return success
            }

            else -> {
                pos.x = pos.x + dir.x
                pos.y = pos.y + dir.y
                return true
            }

        }

        return false
    }

    val directionByCommand = mapOf(
        '^' to Point(-1, 0),  // up
        '>' to Point(0, 1),   // right
        'v' to Point(1, 0),   // down
        '<' to Point(0, -1)   // left
    )

    override fun partTwo() = NOT_IMPLEMENTED
}
