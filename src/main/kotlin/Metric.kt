import kotlin.collections.ArrayDeque

fun interface Metric {
    fun process(node: Node)
}

fun computeMetric(root: Node, metric: Metric) {
    val stack = ArrayDeque<Node>()
    stack.addLast(root)

    while (stack.isNotEmpty()) {
        val currentNode = stack.removeLast()
        metric.process(currentNode)

        if (currentNode.trueBranch != null && currentNode.falseBranch != null) {
            stack.addLast(currentNode.falseBranch)
            stack.addLast(currentNode.trueBranch)
        }
    }
}

class CommonMetrics(
    var decisions: Int = 0,
    var leafs: Int = 0
)

fun computeCommonMetrics(root: Node): CommonMetrics {
    val data = CommonMetrics()
    computeMetric(root) { node ->
        if (node.trueBranch == null) {
            data.leafs++
        } else {
            data.decisions++
        }
    }
    return data
}
