import ComparisonOperator.EQUALS
import ComparisonOperator.LESS_THAN
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test

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
        `test tree of length 4`(tree)
    }

    @Test
    fun `custom length 4`() {
        val tree = `custom tree of length 4`()
        `test tree of length 4`(tree)
    }

    private fun runTestCases(testCases: List<Pair<IntArray, IntArray>>, tree: Node) {
        testCases.forEach { (input, expected) ->
            val actual = interpretProgram(tree, input)
            assertArrayEquals(expected, actual) {
                getFailMessage(input, actual, expected)
            }
        }
    }

    private fun getFailMessage(input: IntArray, actual: IntArray, expected: IntArray) =
        """Input: ${input.contentToString()}
Expected: ${expected.contentToString()}
Actual: ${actual.contentToString()}"""

    private fun `test tree of length 4`(tree: Node) {
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
}

fun `custom tree of length 4`(): Node {
    val `return D` = Output(3)
    val `return C` = Output(2)
    val `return C,D` = Output(2, 3)
    val `return B` = Output(1)
    val `return B,D` = Output(1, 3)
    val `return B,C` = Output(1, 2)
    val `return B,C,D` = Output(1, 2, 3)
    val `return A` = Output(0)
    val `return A,D` = Output(0, 3)
    val `return A,C` = Output(0, 2)
    val `return A,C,D` = Output(0, 2, 3)
    val `return A,B` = Output(0, 1)
    val `return A,B,D` = Output(0, 1, 3)
    val `return A,B,C` = Output(0, 1, 2)
    val `return A,B,C,D` = Output(0, 1, 2, 3)

    val `6 if C = D` = Decision(2, EQUALS, 3, `return C,D`, `return D`)

    val `5 if C ( D` = Decision(2, LESS_THAN, 3, `return C`, `6 if C = D`)

    val `4 if B = D` = Decision(1, EQUALS, 3, `return B,C,D`, `return D`)
    val `4 if B ( D` = Decision(1, LESS_THAN, 3, `return B,C`, `4 if B = D`)
    val `4 if B = C` = Decision(1, EQUALS, 2, `4 if B ( D`, `5 if C ( D`)

    val `3 if B = D` = Decision(1, EQUALS, 3, `return B,D`, `return D`)
    val `3 if B ( D` = Decision(1, LESS_THAN, 3, `return B`, `3 if B = D`)
    val `3 if B ( C` = Decision(1, LESS_THAN, 2, `3 if B ( D`, `4 if B = C`)

    val `2 if A = D` = Decision(0, EQUALS, 3, `return A,B,D`, `return D`)
    val `2 if A ( D` = Decision(0, LESS_THAN, 3, `return A,B`, `2 if A = D`)
    val `2 if A = D'` = Decision(0, EQUALS, 3, `return A,B,C,D`, `return D`)
    val `2 if A ( D'` = Decision(0, LESS_THAN, 3, `return A,B,C`, `2 if A = D'`)
    val `2 if C = D` = Decision(2, EQUALS, 3, `return C,D`, `return C`)
    val `2 if A = C` = Decision(0, EQUALS, 2, `2 if A ( D'`, `2 if C = D`)
    val `2 if A ( C` = Decision(0, LESS_THAN, 2, `2 if A ( D`, `2 if A = C`)
    val `2 if A = B` = Decision(0, EQUALS, 1, `2 if A ( C`, `3 if B ( C`)

    val `1 if A = D` = Decision(0, EQUALS, 3, `return A,D`, `return D`)
    val `1 if A ( D` = Decision(0, LESS_THAN, 3, `return A`, `1 if A = D`)
    val `1 if A = D'` = Decision(0, EQUALS, 3, `return A,C,D`, `return D`)
    val `1 if A ( D'` = Decision(0, LESS_THAN, 3, `return A,C`, `1 if A = D'`)
    val `1 if A = C` = Decision(0, EQUALS, 2, `1 if A ( D'`, `return C`)
    val `1 if A ( C` = Decision(0, LESS_THAN, 2, `1 if A ( D`, `1 if A = C`)
    val `1 if A ( B` = Decision(0, LESS_THAN, 1, `1 if A ( C`, `2 if A = B`)

    return `1 if A ( B`
}

fun main() {
    val tree = `custom tree of length 4`()
    val code = generatePython(tree)
    println(code)
}
