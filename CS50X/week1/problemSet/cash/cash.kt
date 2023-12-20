val QUARTERS = 25
val DIMES = 10
val NICKELS = 5
val PENNIES = 1

fun main() {
    var cents = get_cents()

    var quarters = calculate_quarters(cents)
    cents = cents - quarters * 25

    var dimes = calculate_dimes(cents);
    cents = cents - dimes * 10

    var nickels = calculate_nickels(cents)
    cents = cents - nickels * 5

    var pennies = calculate_pennies(cents)
    cents = cents - pennies * 1

    var coins = quarters + dimes + nickels + pennies

    println("${coins}")
}

fun get_int(prompt: String): Int {
    print(prompt)
    val input = readLine()
    val number = input?.toInt() ?: 0
    return number
}

fun get_cents(): Int {
    var number = get_int("Change owed: ")

    while (number < 0) {
        number = get_int("Change owed: ")
    }

    return number
}
fun calculate_quarters(cents: Int) : Int {
    var num = 0
    var temp = cents
    while (temp >= QUARTERS) {
        temp -= QUARTERS
        num += 1
    }

    return num
}
fun calculate_dimes(cents: Int) : Int {
    var num = 0
    var temp = cents
    while (temp >= DIMES) {
        temp -= DIMES
        num += 1
    }

    return num
}
fun calculate_nickels(cents: Int) : Int {
    var num = 0
    var temp = cents
    while (temp >= NICKELS) {
        temp -= NICKELS
        num += 1
    }

    return num
}
fun calculate_pennies(cents: Int) : Int {
    var num = 0
    var temp = cents
    while (temp >= PENNIES) {
        temp -= PENNIES
        num += 1
    }

    return num
}
