val BITS_IN_BYTE = 8

fun main() {
    val message = get_string("Message: ")

    for (item in message) {
        val bit = item.toInt()

        for (i in BITS_IN_BYTE - 1 downTo 0) {
            print_bulb((bit shr i) % 2)
        }

        println()
    }
}

fun get_string(prompt: String) : String {
    print(prompt)
    val input = readln()

    return input
}

fun print_bulb(bit: Int) {
    val temp = bit

    if (temp == 0)
    {
        print("⚫")
    }

    else if (temp == 1)
    {
        print("\uD83D\uDFE1")
    }
}