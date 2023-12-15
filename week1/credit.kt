val MASTERCARD = arrayOf(51,52,53,54,55)
val AMEX = arrayOf(34,37)

fun main() {
    val card = get_long("Number: ")
    val arraySize = get_length(card)
    val array = fillArray(card, arraySize)

    if (isValid(array, arraySize)) {
        val firstDigit = array[arraySize - 1].toInt()
        val secondDigit = array[arraySize - 2].toInt()

        if (firstDigit == 4 && (arraySize == 13 || arraySize == 16)) {
            println("VISA")
        }

        else if (MASTERCARD.contains(firstDigit * 10 + secondDigit) && arraySize == 16) {
            println("MASTERCARD")
        }

        else if (AMEX.contains(firstDigit * 10 + secondDigit) && arraySize == 15) {
            println("AMEX")
        }

        else {
            println("INVALID")
        }
    }
    else {
        println("INVALID")
    }

}

fun get_long(prompt: String) : Long {
    print("Number: ")
    var input = readLine()

    try {
        var number = input?.toLong() ?: 0
        return number
    }

    catch (e: NumberFormatException) {
        return get_long(prompt)
    }
}

fun get_length(card: Long) : Int {
    var count = 0
    var temp = card

    while (temp > 0) {
        temp /= 10
        count += 1
    }

    return count
}

fun fillArray(card: Long, size: Int) : LongArray {
    var array = LongArray(size)
    var temp = card

    for (i in 0 until size) {
        array[i] = temp % 10
        temp /= 10
    }

    return array
}

fun isValid(array: LongArray, size: Int) : Boolean {
    var index = 1
    var sum = 0

    while (index < size) {
        var value = 0
        value = array[index].toInt() * 2

        while (value > 0) {
            sum += (value % 10)
            value /= 10
        }

        index += 2
    }

    index = 0

    while (index < size) {
        sum += array[index].toInt()
        index += 2
    }

    return (sum % 10) == 0
}
