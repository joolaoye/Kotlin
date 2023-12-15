fun main() {
    print("Height: ")
    var input = readLine()
    val height = input?.toInt() ?: 0

    for (row in 1..height) {
        var space = height - row
        var col = height - space

        for (it in 1..space) {
            print(" ")
        }

        for (it in 1..col) {
            print("#")
        }

        print(" ")

        for (it in 1..col) {
            print("#")
        }

        for (it in 1..space) {
            print(" ")
        }

        println()
    }
}
