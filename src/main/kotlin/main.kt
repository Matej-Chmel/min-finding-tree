import java.io.File
import kotlin.math.pow

fun main() {
    val inputLengths = 2..15
    // Compute decision counts for table and chart
    val decisionCounts = inputLengths.map { len ->
        val tree = generateTree(len)
        countDecisionNodes(tree)
    }
    val expectedCounts = inputLengths.map { len ->
        (3.0.pow(len - 1) - 1.0).toInt()
    }

    val tableRows = inputLengths.zip(decisionCounts).zip(expectedCounts).joinToString("\n") { data ->
        val len = data.first.first
        val decisions = data.first.second
        val expected = data.second
        "    <tr><td>$len</td><td>$decisions</td><td>$expected</td></tr>"
    }
    val inputLengthsJs = inputLengths.joinToString(",")
    val decisionCountsJs = decisionCounts.joinToString(",")

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
                <tr>
                    <th>Input Length</th>
                    <th>Decision Nodes</th>
                    <th>Expected Count</th>
                </tr>
        $tableRows
            </table>
            <!-- Line plot canvas -->
            <canvas id="lineChart" style="width:50vw; height:50vh;"></canvas>
            <script>
                const ctx = document.getElementById('lineChart').getContext('2d');
                const chart = new Chart(ctx, {
                    type: 'line',
                    data: {
                        labels: [$inputLengthsJs],
                        datasets: [{
                            label: 'Decision Nodes',
                            data: [$decisionCountsJs],
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

    File("index.html").writeText(htmlContent)
    println("index.html generated.")
}
