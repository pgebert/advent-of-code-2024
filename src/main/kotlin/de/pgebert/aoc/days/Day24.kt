package de.pgebert.aoc.days

import de.pgebert.aoc.Day

class Day24(input: String? = null) : Day(24, "Day24", input) {

    private val wires: MutableMap<String, Int> = parseWires(inputList)
    private val gates: MutableList<Gate> = parseGates(inputList)


    private fun parseWires(input: List<String>): MutableMap<String, Int> =
        input
            .takeWhile { it.isNotEmpty() }
            .associate { it.substringBefore(":") to it.last().digitToInt() }
            .toMutableMap()

    private fun parseGates(input: List<String>): MutableList<Gate> =
        input
            .dropWhile { it.isNotEmpty() }
            .drop(1)
            .map { Gate.of(it) }
            .toMutableList()

    private data class Gate(val left: String, val right: String, val op: String, val out: String) {
        companion object {
            fun of(input: String): Gate =
                input.split(" ").let { Gate(it[0], it[2], it[1], it[4]) }
        }
    }


    override fun partOne(): Long {

        val values = mutableMapOf<String, () -> Int>()

        inputList.filter { it.isNotBlank() }.forEach { line ->
            if (line.contains(":")) {
                val splitted = line.split(":")
                val key = splitted[0]
                val value = { splitted[1].trim().toInt() }
                values.put(key, value)
            } else {
                val operationRegex = "(\\S+)\\s(\\S+)\\s(\\S+)\\s->\\s(\\S+)".toRegex()
                val (operand1, operation, operand2, target) = operationRegex.matchEntire(line)!!.destructured
                val key = target
                val op = operation.toOperation()
                val value = { op.invoke(values[operand1]!!.invoke(), values[operand2]!!.invoke()) }
                values.put(key, value)
            }
        }

        return values.keys.sortedDescending().filter { it.startsWith("z") }.map { values[it]!!.invoke() }
            .joinToString("").toLong(2)
    }

    private fun String.toOperation(): (Int, Int) -> Int = when (this) {
        "OR" -> Int::or
        "XOR" -> Int::xor
        "AND" -> Int::and
        else -> throw RuntimeException("Unknown operation: $this")
    }

    override fun partTwo() {
        val z = gates.filter { it.out.startsWith("z") }.map { it.out }.sorted().joinToString("->")
        val x = z.replace('z', 'x')
        val y = z.replace('z', 'y')

        println(
            """
            digraph G {
                subgraph {
                   node [style=filled,color=green]
                    $z
                }
                subgraph {
                    node [style=filled,color=gray]
                    $x
                }
                subgraph {
                    node [style=filled,color=gray]
                    $y
                }
                subgraph {
                    node [style=filled,color=pink]
                    ${gates.filter { gate -> gate.op == "AND" }.joinToString(" ") { gate -> gate.out }}
                }
                subgraph {
                    node [style=filled,color=yellow];
                    ${gates.filter { gate -> gate.op == "OR" }.joinToString(" ") { gate -> gate.out }}
                }
                subgraph {
                    node [style=filled,color=lightblue];
                    ${gates.filter { gate -> gate.op == "XOR" }.joinToString(" ") { gate -> gate.out }}
                }
                """.trimIndent()
        )
        gates.forEach { (left, right, _, out) ->
            println("    $left -> $out")
            println("    $right -> $out")
        }
        println("}")
    }
}
