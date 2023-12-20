import kotlin.system.exitProcess

var isOnlyLetters : (String) -> Boolean = {string -> string.all {it.isLetter()} }

fun main(argv: Array<String>) {
    val argc = argv.size

    if (argc != 1) {
        println("Enter only one command line argument")
        exitProcess(1)
    }

    val key = argv.get(0)

    if (isOnlyLetters(key) == false || key.length != 26) {
        println("Enter 26 alphabet characters")
        exitProcess(1)
    }

    val string = get_string("plaintext: ")
    println("ciphertext: ${rotate_string(string, key)}")
}

fun get_string(prompt: String) : String {
    print(prompt)
    val input = readln()

    return input
}

fun rotate_string(string: String, key: String) : String {
    var temp = StringBuilder(string)

    for (i in 0 until temp.length) {
        val index = (temp[i].toInt() % 32) - 1
        temp[i] = key[index]
    }

    return temp.toString()
}