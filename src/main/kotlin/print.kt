import TaskType.*
import java.io.File

enum class TaskType {
    PROCESS_NODE,
    PROCESS_ELSE
}

data class StackEntry(val node: Node, val indent: Int, val taskType: TaskType)

fun generatePython(root: Node): String {
    val builder = StringBuilder()
    val stack = ArrayDeque<StackEntry>()
    stack.addLast(StackEntry(root, 0, PROCESS_NODE))
    val labels = "ABCDEFGH"

    while (stack.isNotEmpty()) {
        val entry = stack.removeLast()
        val node = entry.node
        val indent = entry.indent
        val indentStr = "    ".repeat(indent)

        when (entry.taskType) {
            PROCESS_NODE -> {
                if (node.trueBranch == null) {
                    // Leaf node: output the return statement.
                    val indices = node.indices.map { labels[it] }.joinToString(", ")
                    builder.append(indentStr).append("return ").append(indices).append("\n")
                } else {
                    // Decision node: output an if statement.
                    val left = labels[node.indices[0]]
                    val right = labels[node.indices[1]]
                    val op = when (node.operator) {
                        ComparisonOperator.LESS_THAN -> "<"
                        ComparisonOperator.EQUALS -> "=="
                        ComparisonOperator.GREATER_THAN -> ">"
                    }
                    builder.append(indentStr).append("if ").append(left).append(" $op ").append(right).append(":\n")
                    // Push the false branch as an else task (it will add the "else:" line).
                    stack.addLast(StackEntry(node.falseBranch!!, indent, PROCESS_ELSE))
                    // Then push the true branch for processing with increased indent.
                    stack.addLast(StackEntry(node.trueBranch, indent + 1, PROCESS_NODE))
                }
            }
            PROCESS_ELSE -> {
                // For an else task, output the "else:" line and then process the node.
                builder.append(indentStr).append("else:\n")
                stack.addLast(StackEntry(node, indent + 1, PROCESS_NODE))
            }
        }
    }
    return builder.toString().trimEnd()
}

fun writeFile(fullName: String, content: String) {
    File(fullName).writeText(content)
    println("Written: $fullName")
}
