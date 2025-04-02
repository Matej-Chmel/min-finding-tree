import kotlin.math.pow

class TreeRecord(
    val length: Int,
    val actualDecisions: Int,
    val expectedDecisions: Int,
    val actualLeafs: Int
) {
    companion object {
        fun getTableHeader() = """
            <tr>
                <th>Input Length</th>
                <th>Expected Decisions</th>
                <th>Actual Decisions</th>
                <th>Actual Leafs</th>
            </tr>
        """.trimIndent()
    }

    fun getTableRow() = """
        <tr>
            <td>$length</td>
            <td>$expectedDecisions</td>
            <td>$actualDecisions</td>
            <td>$actualLeafs</td>
        </tr>
    """.trimIndent()
}

fun generateWebpage(inputLengths: IntRange): String {
    val records = inputLengths.map { len ->
        val root = generateTree(len)
        val metrics = computeCommonMetrics(root)
        TreeRecord(
            length = len,
            actualDecisions = metrics.decisions,
            expectedDecisions = (3.0.pow(len - 1) - 1.0).toInt(),
            actualLeafs = metrics.leafs
        )
    }

    val rows = records.map(TreeRecord::getTableRow)
    val jsLengths = inputLengths.joinToString(prefix = "[", postfix = "]", separator = ",")
    val jsActualDecisions = records
        .asSequence().map(TreeRecord::actualDecisions)
        .joinToString(prefix = "[", postfix = "]", separator = ",")

    val htmlContent = """
        <!DOCTYPE html>
        <html>
        <head>
            <title>Decision Tree Summary</title>
            <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        </head>
        <body>
            <h1>Decision Tree Summary</h1>
            <table border="1">
                ${TreeRecord.getTableHeader()}
                ${rows.joinToString("\n")}
            </table>
            <!-- Line plot canvas -->
            <canvas id="lineChart" style="width:50vw; height:50vh;"></canvas>
            <script>
                const ctx = document.getElementById('lineChart').getContext('2d');
                const chart = new Chart(ctx, {
                    type: 'line',
                    data: {
                        labels: $jsLengths,
                        datasets: [{
                            label: 'Decision Nodes',
                            data: $jsActualDecisions,
                            borderColor: 'rgba(75, 192, 192, 1)',
                            fill: false
                        }]
                    },
                    options: {
                        scales: {
                            x: { title: { display: true, text: 'Input Length'} },
                            y: { title: { display: true, text: 'Decision Nodes'} }
                        }
                    }
                });
                chart.height("50%");
                chart.width("50%");
            </script>
        </body>
        </html>
    """.trimIndent()

    return htmlContent
}
