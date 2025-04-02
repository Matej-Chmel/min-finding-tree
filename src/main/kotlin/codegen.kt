import ComparisonOperator.*

fun generateTree(inputLength: Int): Node {
    val falseFalseBranch = generateSubtree(
        minimums = intArrayOf(1),
        nextIndex = 2,
        inputLength = inputLength
    )
    val falseTrueBranch = generateSubtree(
        minimums = intArrayOf(0, 1),
        nextIndex = 2,
        inputLength = inputLength
    )
    val falseBranch = Node(
        trueBranch = falseTrueBranch,
        falseBranch = falseFalseBranch,
        operator = EQUALS,
        indices = intArrayOf(0, 1)
    )
    val trueBranch = generateSubtree(intArrayOf(0), 2, inputLength)

    val root = Node(
        trueBranch = trueBranch,
        falseBranch = falseBranch,
        operator = LESS_THAN,
        indices = intArrayOf(0, 1)
    )
    return root
}

fun generateSubtree(minimums: IntArray, nextIndex: Int, inputLength: Int): Node {
    if (nextIndex == inputLength) {
        return Node(indices = minimums)
    }

    val falseTrueMinimums = minimums + nextIndex
    val falseTrueBranch = generateSubtree(falseTrueMinimums, nextIndex + 1, inputLength)
    val falseFalseBranch = generateSubtree(minimums, nextIndex + 1, inputLength)
    val falseBranch = Node(
        trueBranch = falseTrueBranch,
        falseBranch = falseFalseBranch,
        operator = EQUALS,
        indices = intArrayOf(nextIndex, minimums.first())
    )
    val trueBranch = generateSubtree(
        intArrayOf(nextIndex),
        nextIndex + 1,
        inputLength
    )
    val node = Node(
        trueBranch = trueBranch,
        falseBranch = falseBranch,
        operator = LESS_THAN,
        indices = intArrayOf(nextIndex, minimums.first())
    )
    return node
}
