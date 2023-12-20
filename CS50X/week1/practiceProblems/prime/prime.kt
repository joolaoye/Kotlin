fun main() {
    print("Minimum: ")
    var input: String? = readLine()
    val minimum: Int = input?.toInt() ?: 0

    print("Maximum: ")
    input = readLine()
    val maximum: Int = input?.toInt() ?: 0

    checkPrimes(minimum, maximum)
}

fun checkPrimes(lowerBound: Int, upperBound: Int) {
    val array = BooleanArray(upperBound + 1) {true}

    array[0] = false
    array[1] = false

    var offset = 2

    while (offset * offset <= upperBound) {
        var check = offset * offset

        while (check <= upperBound) {
            array[check] = false
            check += offset
        }

        offset += 1
    }

    for (i in lowerBound..upperBound) {
        if (array[i]) {
            println(i)
        }
    }
}
