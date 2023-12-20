val LEAST = 9

fun main() {
    var start = getInteger("Start size: ")
    while (start < LEAST) {
        start = getInteger("Start size: ")
    }
    var end = getInteger("End size: ")
    while (end < start) {
        end = getInteger("End size: ")
    }

    var years = 0

    while (start < end) {
        var new = start / 3
        var dead = start / 4

        years += 1
        start = start + new - dead
    }

    println("Years: ${years}")
}

fun getInteger(prompt: String) : Int {
    var number = 0


    while (number < 1) {
        print(prompt)
        var input = readLine()
        number = input?.toInt() ?: 0
    }

    return number
}
