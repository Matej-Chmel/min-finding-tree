fun main() {
    val root = generateTree(inputLength = 4)
    val chart = generateFlowchart(root)
    val page = generateWebpage(2..15)
    writeFile("flowchart.md", chart)
    writeFile("index.html", page)
}
