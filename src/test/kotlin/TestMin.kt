import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test

fun getFailMessage(actual: IntArray, expected: IntArray): String {
    return "Expected ${expected.contentToString()} but got ${actual.contentToString()}"
}

class MinDecisionTreeTest {

    @Test
    fun `length 2`() {
        val tree = generateTree(2)
        val testCases = listOf(
            intArrayOf(0, 0) to intArrayOf(0, 0),
            intArrayOf(0, 1) to intArrayOf(0),
            intArrayOf(1, 0) to intArrayOf(0),
            intArrayOf(1, 1) to intArrayOf(1, 1)
        )
        runTestCases(testCases, tree)
    }

    @Test
    fun `length 3`() {
        val tree = generateTree(3)
        val testCases = listOf(
            intArrayOf(0, 0, 0) to intArrayOf(0, 0, 0),
            intArrayOf(0, 0, 1) to intArrayOf(0, 0),
            intArrayOf(0, 1, 0) to intArrayOf(0, 0),
            intArrayOf(0, 1, 1) to intArrayOf(0),
            intArrayOf(1, 0, 0) to intArrayOf(0, 0),
            intArrayOf(1, 0, 1) to intArrayOf(0),
            intArrayOf(1, 1, 0) to intArrayOf(0),
            intArrayOf(1, 1, 1) to intArrayOf(1, 1, 1)
        )
        runTestCases(testCases, tree)
    }

    @Test
    fun `length 4`() {
        val tree = generateTree(4)
        val testCases = listOf(
            intArrayOf(0, 0, 0, 0) to intArrayOf(0, 0, 0, 0),
            intArrayOf(0, 0, 0, 1) to intArrayOf(0, 0, 0),
            intArrayOf(0, 0, 1, 0) to intArrayOf(0, 0, 0),
            intArrayOf(0, 0, 1, 1) to intArrayOf(0, 0),
            intArrayOf(0, 1, 0, 0) to intArrayOf(0, 0, 0),
            intArrayOf(0, 1, 0, 1) to intArrayOf(0, 0),
            intArrayOf(0, 1, 1, 0) to intArrayOf(0, 0),
            intArrayOf(0, 1, 1, 1) to intArrayOf(0),
            intArrayOf(1, 0, 0, 0) to intArrayOf(0, 0, 0),
            intArrayOf(1, 0, 0, 1) to intArrayOf(0, 0),
            intArrayOf(1, 0, 1, 0) to intArrayOf(0, 0),
            intArrayOf(1, 0, 1, 1) to intArrayOf(0),
            intArrayOf(1, 1, 0, 0) to intArrayOf(0, 0),
            intArrayOf(1, 1, 0, 1) to intArrayOf(0),
            intArrayOf(1, 1, 1, 0) to intArrayOf(0),
            intArrayOf(1, 1, 1, 1) to intArrayOf(1, 1, 1, 1)
        )
        runTestCases(testCases, tree)
    }

    private fun runTestCases(testCases: List<Pair<IntArray, IntArray>>, tree: Node) {
        testCases.forEach { (input, expected) ->
            val actual = interpretProgram(tree, input)
            assertArrayEquals(expected, actual) {
                getFailMessage(actual, expected)
            }
        }
    }
}
