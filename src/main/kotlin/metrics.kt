import kotlin.collections.ArrayDeque

fun countDecisionNodes(root: Node): Int {
    var count = 0
    val stack = ArrayDeque<Node>()
    stack.addLast(root)

    while (stack.isNotEmpty()) {
        val currentNode = stack.removeLast()
        if (currentNode.trueBranch == null) {
            // leaf
            continue
        }

        count++
        stack.addLast(currentNode.falseBranch!!)
        stack.addLast(currentNode.trueBranch!!)
    }

    return count
}
