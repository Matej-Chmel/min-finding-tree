fun generateFlowchart(root: Node): String {
    val sb = StringBuilder()
    sb.append("graph TD\n")
    var counter = 0
    val nodeIds = mutableMapOf<Node, String>()

    fun traverse(node: Node) {
        if (node in nodeIds) return
        val nodeId = "node" + counter++
        nodeIds[node] = nodeId
        val isDecision = node.trueBranch != null && node.falseBranch != null && node.indices.size == 2

        if (isDecision) {
            val label = "${node.indices[0]} ${node.operator} ${node.indices[1]}"
            sb.append("$nodeId{ $label }\n")
            node.trueBranch?.let {
                traverse(it)
                sb.append("$nodeId -- true --> ${nodeIds[it]}\n")
            }
            node.falseBranch?.let {
                traverse(it)
                sb.append("$nodeId -- false --> ${nodeIds[it]}\n")
            }
        } else {
            val label = node.indices.joinToString(", ")
            sb.append("$nodeId[/ $label /]\n")
        }
    }

    traverse(root)
    return sb.toString()
}
