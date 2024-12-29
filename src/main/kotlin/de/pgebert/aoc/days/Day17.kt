package de.pgebert.aoc.days

import de.pgebert.aoc.Day

class Day17(input: String? = null) : Day(17, "Day17", input) {

//    override fun partOne(): String {
//
//        val register = mutableMapOf(
//            'A' to 0L,
//            'B' to 0L,
//            'C' to 0L,
//        )
//
//        val program = mutableListOf<Long>()
//
//        inputList.forEach { line ->
//            when {
//                line.startsWith("Register A:") -> register['A'] = line.removePrefix("Register A:").trim().toLong()
//                line.startsWith("Register B:") -> register['B'] = line.removePrefix("Register B:").trim().toLong()
//                line.startsWith("Register C:") -> register['C'] = line.removePrefix("Register C:").trim().toLong()
//                line.startsWith("Program:") -> program.addAll(
//                    line.removePrefix("Program:").trim().split(",").map { it.toLong() })
//            }
//        }
//
//        fun Long.toLiteral() = when (this) {
//            0L, 1L, 2L, 3L -> this
//            4L -> register['A']!!
//            5L -> register['B']!!
//            6L -> register['C']!!
//            else -> {
//                throw IllegalArgumentException("Invalid combo value: $this")
//            }
//        }
//
//        var pointer = 0
//        var out = mutableListOf<Long>()
//
//        fun getOperationByOpcode(opcode: Long): (Long) -> Unit = when (opcode) {
//            // adv
//            0L -> { operand: Long ->
//                register['A'] = register['A']!! / 2L.pow(operand.toLiteral())
//                pointer = pointer + 2
//            }
//            // blx
//            1L -> { operand: Long ->
//                register['B'] = register['B']!!.xor(operand)
//                pointer = pointer + 2
//            }
//            // bst
//            2L -> { operand: Long ->
//                register['B'] = operand.toLiteral() % 8
//                pointer = pointer + 2
//            }
//            // jnz
//            3L -> { operand: Long ->
//                pointer = if (register['A']!! == 0L) pointer else operand.toInt()
//                if (register['A']!! == 0L && program[pointer] == 3L) pointer = Int.MAX_VALUE
//            }
//            // bxc
//            4L -> { operand: Long ->
//                register['B'] = register['B']!!.xor(register['C']!!)
//                pointer = pointer + 2
//            }
//            // out
//            5L -> { operand: Long ->
//                out.add(operand.toLiteral() % 8)
//                pointer = pointer + 2
//            }
//            // bdv
//            6L -> { operand: Long ->
//                register['B'] = register['A']!! / 2L.pow(operand.toLiteral())
//                pointer = pointer + 2
//            }
//            // cdv
//            7L -> { operand: Long ->
//                register['C'] = register['A']!! / 2L.pow(operand.toLiteral())
//                pointer = pointer + 2
//            }
//
//            else -> { _: Long -> throw IllegalArgumentException("Invalid operation: $this") }
//        }
//
//
//
//        while (pointer < program.size - 1) {
//            val operation = getOperationByOpcode(program[pointer])
//            operation(program[pointer + 1])
//        }
//
//
//
//        return out.joinToString(",")
//    }
//
//    private fun Long.pow(exponent: Long) = toDouble().pow(exponent.toDouble()).toInt()


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


    override fun partOne(): String {

        val registerA = inputList.elementAt(0).removePrefix("Register A:").trim().toLong()
        val registerB = inputList.elementAt(1).removePrefix("Register B:").trim().toLong()
        val registerC = inputList.elementAt(2).removePrefix("Register C:").trim().toLong()
        val program = inputList.elementAt(4).removePrefix("Program:").trim().split(",").map { it.toInt() }

        val computer = Computer(registerA, registerB, registerC, program)

        return computer.runToEnd().joinToString(",")
    }


    override fun partTwo(): Long {

        val computer = Computer(0, 0, 0, listOf(2, 4, 1, 1, 7, 5, 4, 7, 1, 4, 0, 3, 5, 5, 3, 0))


        return computer.program
            .reversed()
            .map { it.toLong() }
            .fold(listOf(0L)) { candidates, instruction ->
                candidates.flatMap { candidate ->
                    val shifted = candidate shl 3
                    (shifted..shifted + 8).mapNotNull { attempt ->
                        computer.copy().run {
                            registerA = attempt
                            attempt.takeIf { runToEnd().first() == instruction }
                        }
                    }
                }
            }.first { computer.copy(registerA = it).runToEnd().map { it.toInt() } == computer.program }

    }

}
