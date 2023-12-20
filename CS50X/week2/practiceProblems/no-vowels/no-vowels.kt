import kotlin.system.exitProcess

fun main(argv: Array<String>) {
    val argc: Int = argv.size

    if (argc != 1) {
        println("Enter only one command line argument")
        exitProcess(1)
    }

    println(replace(argv[0]))

}

fun replace(string: String) : String {
    var temp = StringBuilder(string)
    for (i in 0 until string.length) {
        temp[i] = when (string[i]) {
            'a' -> '6'
            'e' -> '3'
            'i' -> '1'
            'o' -> '0'
            else -> temp[i]
        }
    }

    return temp.toString()
}
