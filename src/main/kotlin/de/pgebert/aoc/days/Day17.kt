package de.pgebert.aoc.days

import de.pgebert.aoc.Day

class Day17(input: String? = null) : Day(17, "Day17", input) {

    private val registerA = inputList.elementAt(0).removePrefix("Register A:").trim().toLong()
    private val registerB = inputList.elementAt(1).removePrefix("Register B:").trim().toLong()
    private val registerC = inputList.elementAt(2).removePrefix("Register C:").trim().toLong()
    private val program = inputList.filterNot { it.isBlank() }.elementAt(3).removePrefix("Program:").trim().split(",")
        .map { it.toInt() }

    data class Computer(
        var registerA: Long,
        var registerB: Long,
        var registerC: Long,
        val program: List<Int>
    ) {
        private var instructionPointer: Int = 0
        private val output = mutableListOf<Long>()

        fun runToEnd(): List<Long> {
            var executed = true
            while (executed) {
                executed = executeInstruction()
            }
            return output
        }

        fun executeInstruction(): Boolean {
            if (instructionPointer > program.lastIndex) return false
            else {
                val operand = program[instructionPointer + 1]
                when (program[instructionPointer]) {
                    0 -> {
                        registerA = registerA shr operand.toComboOperand().toInt()
                        instructionPointer += 2
                    }

                    1 -> {
                        registerB = registerB xor operand.toLong()
                        instructionPointer += 2
                    }

                    2 -> {
                        registerB = operand.toComboOperand() % 8
                        instructionPointer += 2
                    }

                    3 -> {
                        instructionPointer = if (registerA == 0L) instructionPointer + 2
                        else operand
                    }

                    4 -> {
                        registerB = registerB xor registerC
                        instructionPointer += 2
                    }

                    5 -> {
                        output.add(operand.toComboOperand() % 8)
                        instructionPointer += 2
                    }

                    6 -> {
                        registerB = registerA shr operand.toComboOperand().toInt()
                        instructionPointer += 2
                    }

                    7 -> {
                        registerC = registerA shr operand.toComboOperand().toInt()
                        instructionPointer += 2
                    }
                }
                return true
            }
        }

        private fun Int.toComboOperand(): Long =
            when (this) {
                in 0..3 -> toLong()
                4 -> registerA
                5 -> registerB
                6 -> registerC
                else -> throw IllegalArgumentException("Invalid operand: $this")
            }
    }


    override fun partOne() =
        Computer(registerA, registerB, registerC, program).runToEnd().joinToString(",")


    override fun partTwo(): Long {

        val computer = Computer(0, 0, 0, listOf(2, 4, 1, 1, 7, 5, 4, 7, 1, 4, 0, 3, 5, 5, 3, 0))

        return computer.program
            .reversed()
            .map { it.toLong() }
            .fold(listOf(0L)) { candidates, instruction ->
                candidates.flatMap { candidate ->
                    val shifted = candidate shl 3
                    (shifted..shifted + 8).mapNotNull { attempt ->
                        computer.copy(registerA = attempt).run {
                            attempt.takeIf { runToEnd().first() == instruction }
                        }
                    }
                }
            }.first { candidate ->
                computer.copy(registerA = candidate).runToEnd().map { it.toInt() } == computer.program
            }

    }

}
