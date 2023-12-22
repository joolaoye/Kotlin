import kotlin.math.pow
import kotlin.system.exitProcess

fun main() {
    val input = get_string("Enter a positive integer: ")

    for (i in 0 until input.length) {
        if (!input[i].isDigit()) {
            println("Invalid Input!")
            exitProcess(1)
        }
    }

    println(convert(input))
}

fun get_string(prompt: String) : String {
    print(prompt)
    val input = readln()

    return input
}

fun convert(input: String) : Int {

    val n = input.length - 1

    if (n < 1) {
        return input.toInt()
    }

    val base = 10.0
    val currentInt: Int = input[0].digitToInt()

    return (((base.pow(n)).toInt() * currentInt)  + (convert(input.substring(1))))
}
