import ComparisonOperator.*

enum class ComparisonOperator {
    LESS_THAN, EQUALS, GREATER_THAN;

    override fun toString() = when (this) {
        LESS_THAN -> "<"
        EQUALS -> "=="
        GREATER_THAN -> ">"
    }
}

class Node (
    val trueBranch: Node? = null,
    val falseBranch: Node? = null,
    val operator: ComparisonOperator = LESS_THAN,
    val indices: IntArray = intArrayOf()
)

fun Decision(
    left: Int, operator: ComparisonOperator, right: Int,
    ifTrue: Node, ifFalse: Node
) =
    Node(
        trueBranch = ifTrue,
        falseBranch = ifFalse,
        operator = operator,
        indices = intArrayOf(left, right)
    )

fun Output(vararg indices: Int) = Node(indices = indices)
