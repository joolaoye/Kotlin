fun main() {
    val weeks = get_int("Number of weeks taking CS50: ")

    var hours = IntArray(weeks){0}

    for (i in 0 until weeks)
    {
        hours[i] = get_int("Week ${i} HW Hours: ")
    }

    var output: Char = ' '

    while (output != 'T' && output != 'A')
    {
        output = get_char("Enter T for total hours, A for average hours per week: ").uppercaseChar()
    }

    println("${"%.2f".format(calc_hours(hours, weeks, output))} hours")
}

fun calc_hours(hours: IntArray, weeks: Int, output: Char) : Float {
    var runningSum = 0f

    for (numHour in hours) {
        runningSum += numHour
    }

    if (output == 'A') {
        return (runningSum / weeks)
    }

    return runningSum
}

fun get_int(prompt: String) : Int {
    var number = 0

    try {
        print(prompt)
        val input = readLine()
        number = input?.toInt() ?: 0
    }

    catch (e: NumberFormatException) {
        return get_int(prompt)
    }

    return number
}

fun get_char(prompt: String) : Char {
    var character = ' '

    try {
        print(prompt)
        val input = readLine()
        character = input?.get(0) ?: throw NoSuchElementException()
    }

    catch (e: NoSuchElementException) {
        return get_char(prompt)
    }

    return character
}
