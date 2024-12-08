package de.pgebert.aoc.days

import de.pgebert.aoc.Day

class Day07(input: String? = null) : Day(7, "Day7", input) {

    override fun partOne() = getEquations().sumOf { equation ->
        equation[0]
            .takeIf {
                val operators = listOf(additionOperator, multiplicationOperator)
                isEquationSolvable2(equation[0], 0, equation.drop(1), operators)
            } ?: 0
    }

    override fun partTwo() = getEquations().sumOf { equation ->
        equation[0]
            .takeIf {
                val operators = listOf(additionOperator, multiplicationOperator, concatenationOperator)
                isEquationSolvable2(equation[0], 0, equation.drop(1), operators)
            } ?: 0
    }

    private fun getEquations() =
        inputList.map { it.split("[:\\s]".toRegex()).filter { it.isNotBlank() }.map { it.toLong() } }


    private fun isEquationSolvable2(
        result: Long,
        acc: Long,
        remaining: List<Long>,
        operators: List<(Long, Long) -> Long>
    ): Boolean {

        if (remaining.isEmpty()) return result == acc

        return operators.any { operator ->
            isEquationSolvable2(
                result,
                operator(acc, remaining.first()),
                remaining.drop(1),
                operators
            )
        }

    }

    private val additionOperator = { a: Long, b: Long -> a + b }
    private val multiplicationOperator = { a: Long, b: Long -> a * b }
    private val concatenationOperator = { a: Long, b: Long -> "$a$b".toLong() }

}
