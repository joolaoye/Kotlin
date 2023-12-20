import java.io.FileReader
import java.io.FileWriter
import kotlin.random.Random
import kotlin.system.exitProcess

val LISTSIZE = 1000

val EXACT = 2
val CLOSE = 1
val WRONG = 0

val GREEN = "\u001B[38;2;255;255;255;1m\u001B[48;2;106;170;100;1m"
val YELLOW = "\u001B[38;2;255;255;255;1m\u001B[48;2;201;180;88;1m"
val RED = "\u001B[38;2;255;255;255;1m\u001B[48;2;220;20;60;1m"
val RESET = "\u001B[0;39m"

val ACCEPTED = arrayOf("5", "6", "7", "8")

fun main(argv: Array<String>) {
    val argc = argv.size

    if (argc != 1) {
        println("Enter only one command line argument: Wordisze")
        exitProcess(1)
    }

    var wordsize = 0

    if (!ACCEPTED.contains(argv[0])) {
        println("Error: wordsize must be either 5,6,7, or 8")
        exitProcess(1)
    }

    wordsize = argv[0].toInt()

    val filename = "${wordsize}.txt"
    var choice : String

    try {
        val file = FileReader(filename)
        val fileContents = file.readLines()

        val randomIndex = Random.nextInt(LISTSIZE)
        choice = fileContents[randomIndex]
    }
    catch (e:Exception) {
        println("Error opening file ${filename}")
        exitProcess(1)
    }

    val guesses = wordsize + 1
    var won = false

    println("${GREEN}This is WORDLE50${RESET}")
    println("You have ${guesses} tries to guess the ${wordsize}-letter word I'm thinking of")

    for (i in 0 until guesses) {
        var guess = get_guess(wordsize)

        var status = IntArray(wordsize){0}

        val score = check_word(guess, wordsize, status, choice)

        print("Guess ${i + 1}: ")

        print_word(guess,wordsize, status)

        if (score == EXACT * wordsize) {
            won = true
            break
        }
    }

    if (won) {
        println("You won!")
    }
    else {
        println(choice)
    }
}

fun get_string(prompt: String) : String {
    print(prompt)
    val input = readln()

    return input
}

fun get_guess(wordsize: Int) : String {
    var input: String

    do {
        input = get_string("Input a ${wordsize}-letter word: ")
    } while (input.length != wordsize)

    return input.lowercase()
}

fun check_word(guess: String, wordsize: Int, status: IntArray, choice: String) : Int {
    var score = 0

    for (i in 0 until wordsize) {
        for (j in 0 until wordsize) {
            if (guess[i] == choice[j] && i == j) {
                status[i] = EXACT
                score +=  EXACT
                break
            }

            else if (guess[i] == choice[j]) {
                status[i] = CLOSE
                score += CLOSE
            }
        }
    }

    return score
}

fun print_word(guess: String, wordsize: Int, status: IntArray) {
    for (i in 0 until wordsize) {
        when (status[i]) {
            2 -> print("${GREEN}")
            1 -> print("${YELLOW}")
            else -> print("${RED}")
        }

        print("${guess[i]}${RESET}")
    }

    println()
}
