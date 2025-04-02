import ComparisonOperator.*

enum class ComparisonOperator {
    LESS_THAN, EQUALS, GREATER_THAN
}

class Node (
    var trueBranch: Node? = null,
    val falseBranch: Node? = null,
    val operator: ComparisonOperator = LESS_THAN,
    val indices: IntArray = intArrayOf()
)
