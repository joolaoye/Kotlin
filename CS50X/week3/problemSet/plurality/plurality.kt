import kotlin.system.exitProcess

val MAX = 9

data class candidate(
    var name: String,
    var votes: Int
)

var candidates = Array<candidate>(MAX){candidate("", 0)}

var candidate_count: Int = 0

fun main(argv: Array<String>) {
    val argc = argv.size

    if (argc < 1) {
        print("Usage: plurality [candidate ...]\n")
        exitProcess(1)
    }

    candidate_count = argc

    if (candidate_count > MAX) {
        print("Maximum number of candidates is ${MAX}")
        exitProcess(2)
    }

    for (i in 0 until candidate_count) {
        candidates[i] = candidate(argv[i], 0)
    }

    val voter_count: Int = get_int("Number of voters: ")

    for (i in 0 until voter_count) {
        var name : String = get_string("Vote: ")

        if (!vote(name)) {
            print("Invalid vote.\n")
        }
    }

    print_winner()
}

fun get_string(prompt: String) : String {
    print(prompt)
    val input = readln()

    return input
}

fun vote(name: String) : Boolean {

    for (i in 0 until candidate_count) {
        if (name == candidates[i].name) {
            candidates[i].votes += 1
            return true
        }
    }

    return false
}

fun print_winner() {
    var max = candidates[0].votes

    for (i in 1 until candidate_count) {
        max = if (max < candidates[i].votes) candidates[i].votes else max
    }

    for (i in 0 until candidate_count) {
        if (max == candidates[i].votes) {
            println(candidates[i].name)
        }
    }
}