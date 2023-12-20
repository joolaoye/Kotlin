fun main() {
    val password = get_string("Enter your password: ")

    if (valid(password)) {
        println("Your password is valid!")
    }

    else {
        println("Your password needs at least one uppercase letter, lowercase letter, number and symbol")
    }
}

fun get_string(prompt: String) : String {
    print(prompt)
    val input = readln()

    return input
}

fun valid(password: String) : Boolean {
    var upperCase = false
    var lowerCase = false
    var digit = false
    var symbol = false

    for (item in password) {
        if (item.isUpperCase()) {
            upperCase = true
        }

        else if (item.isLowerCase()) {
            lowerCase = true
        }

        else if (item.isDigit()) {
            digit = true
        }

        else {
            symbol = true
        }

    }

    return upperCase && lowerCase && digit && symbol
}