import ComparisonOperator.*

fun interpretProgram(root: Node, array: IntArray): IntArray {
    var currentNode = root

    while (true) {
        if (currentNode.trueBranch == null) {
            // Output node
            val output = IntArray(currentNode.indices.size)
            for (i in currentNode.indices.indices) {
                output[i] = array[currentNode.indices[i]]
            }
            return output
        } else {
            val leftValue = array[currentNode.indices[0]]
            val rightValue = array[currentNode.indices[1]]

            when (currentNode.operator) {
                LESS_THAN -> {
                    currentNode = if (leftValue < rightValue) {
                        currentNode.trueBranch!!
                    } else {
                        currentNode.falseBranch!!
                    }
                }

                EQUALS -> {
                    currentNode = if (leftValue == rightValue) {
                        currentNode.trueBranch!!
                    } else {
                        currentNode.falseBranch!!
                    }
                }

                GREATER_THAN -> {
                    currentNode = if (leftValue > rightValue) {
                        currentNode.trueBranch!!
                    } else {
                        currentNode.falseBranch!!
                    }
                }
            }
        }
    }
}