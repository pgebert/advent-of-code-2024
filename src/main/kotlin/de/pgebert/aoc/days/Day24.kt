package de.pgebert.aoc.days

import de.pgebert.aoc.Day

class Day24(input: String? = null) : Day(24, "Day24", input) {

    private val wires: MutableMap<String, Int> = parseWires()
    private val gates: MutableList<Gate> = parseGates()


    private fun parseWires(): MutableMap<String, Int> =
        inputList
            .takeWhile { it.contains(":") }
            .associate { it.substringBefore(":") to it.last().digitToInt() }
            .toMutableMap()

    private fun parseGates(): MutableList<Gate> =
        inputList
            .filterNot { it.contains(":") }
            .filterNot { it.isBlank() }
            .map { Gate.of(it) }
            .toMutableList()

    private data class Gate(val left: String, val right: String, val op: String, val out: String) {
        companion object {
            fun of(input: String): Gate =
                input.split(" ").let { Gate(it[0], it[2], it[1], it[4]) }
        }
    }


    override fun partOne(): Long {

        val values = buildMap<String, () -> Int> {
            wires.forEach {
                put(it.key) { it.value }
            }
            gates.forEach {
                put(it.out) {
                    it.op.toOperation().invoke(get(it.left)!!.invoke(), get(it.right)!!.invoke())
                }
            }
        }

        return values.keys
            .sortedDescending()
            .filter { it.startsWith("z") }
            .map { values[it]!!.invoke() }
            .joinToString("").toLong(2)
    }


    private fun String.toOperation(): (Int, Int) -> Int = when (this) {
        "OR" -> Int::or
        "XOR" -> Int::xor
        "AND" -> Int::and
        else -> throw RuntimeException("Unknown operation: $this")
    }


    override fun partTwo(): String {
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

        // 1 - use output to create graph visualization with graphviz
        // 2 - correct misinformed patterns manually
        // 3 - write down corrected nodes in alphabetical order

        return "fbq,pbv,qff,qnw,qqp,z16,z23,z36"
    }
}
