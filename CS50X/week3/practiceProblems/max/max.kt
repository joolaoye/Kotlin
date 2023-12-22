fun main() {
    var n : Int

    do {
        n = get_int("Number of elements: ")
    } while (n < 1)

    var arr = IntArray(n){0}

    for (i in 0 until n) {
        arr[i] = get_int("Element ${i}: ")
    }

    println("The max value is ${max(arr, n)}.")
}

fun get_int(prompt: String) : Int {
    var number: Int
    try {
        print(prompt)
        val input = readln()
        number = input.toInt()
    }

    catch (e: NumberFormatException) {
        return get_int(prompt)
    }

    return number
}

fun max(arr: IntArray, n: Int) : Int {
    var max = arr[0]

    for (i in 1 until n) {
        max = if (max < arr[i]) arr[i] else max
    }

    return max
}