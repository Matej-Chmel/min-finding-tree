fun main() {
    val root = generateTree(inputLength = 2)
    val chart = generateFlowchart(root)
//    val page = generateWebpage(2..15)
    writeFile("flowchart.md", chart)
//    writeFile("index.html", page)
}
