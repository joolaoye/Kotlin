import kotlin.system.exitProcess

var convert: (Int) -> Char = { number -> number.toChar()}

fun main(argv: Array<String>) {
    val argc = argv.size

    if (argc != 1) {
        print("Enter only one command line argument")
        exitProcess(1)
    }

    val temp = argv.get(0)
    var key: Int

    try {
        key = temp.toInt()
    }
    catch (e: NumberFormatException) {
        println("Enter a number")
        exitProcess(1)
    }

    var plaintext = get_string("plaintext: ")

    println("ciphertext: ${encrypt_string(plaintext, key)}")
}

fun get_string(prompt: String) : String {
    print(prompt)
    val input = readln()

    return input
}

fun encrypt_string(plaintext: String, key: Int) : String {
    var temp = StringBuilder(plaintext)
    var offset: Int

    for (i in 0 until plaintext.length) {
        if (temp[i].isLetter()) {
            if (temp[i].isUpperCase()) {
                offset = 65
            }
            else {
                offset = 97
            }
            temp[i] = convert(offset + (((temp[i].toInt() % 32) - 1 + key) % 26))
        }
    }

    return temp.toString()
}
