import kotlin.system.exitProcess

// Max number of candidates
val MAX =  9

// preferences[i][j] is number of voters who prefer i over j
var preferences = Array(MAX) {Array<Int>(MAX) {0}}

// locked[i][j] means i is locked in over j
var locked = Array(MAX) {Array<Boolean> (MAX) {false}}

// Each pair has a winner, loser
data class pair(
    var winner: Int,
    var loser: Int
)

// Array of candidates
var candidates = Array<String>(MAX) {""}
var pairs = Array<pair>(MAX * (MAX - 1) / 2) {pair(0,0)}

var pair_count: Int = 0
var candidate_count: Int = 0


fun main(argv: Array<String>)
{
    val argc = argv.size
    // Check for invalid usage
    if (argc < 1)
    {
        print("Usage: tideman [candidate ...]\n");
        exitProcess(1)
    }

    // Populate array of candidates
    candidate_count = argc;
    if (candidate_count > MAX)
    {
        print("Maximum number of candidates is $MAX\n")
        exitProcess(2)
    }
    for (i in 0 until candidate_count)
    {
        candidates[i] = argv[i]
    }

    // Clear graph of locked in pairs
    for (i in 0 until candidate_count)
    {
        for (j in 0 until candidate_count)
        {
            locked[i][j] = false
        }
    }

    pair_count = 0
    val voter_count: Int = get_int("Number of voters: ")

    // Query for votes
    for (i in 0 until voter_count)
    {
        // ranks[i] is voter's ith preference
        var ranks = Array<Int>(candidate_count) {0}

        // Query for each rank
        for (j in 0 until candidate_count)
        {
            val name: String = get_string("Rank ${j + 1}: ")

            if (!vote(j, name, ranks))
            {
                print("Invalid vote.\n")
                exitProcess(3)
            }
        }

        record_preferences(ranks)

        print("\n")
    }

    add_pairs()
    sort_pairs()
    lock_pairs()
    print_winner()
}

fun get_int(prompt: String) : Int
{
    var number = 0
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

fun get_string(prompt: String) : String {
    print(prompt)
    return readln()
}
// Update ranks given a new vote
fun vote(rank: Int, name: String, ranks: Array<Int>) : Boolean
{
    for (i in 0 until candidate_count) {
        if (candidates[i] == name) {
            ranks[rank] = i
            return true
        }
    }
    return false
}

// Update preferences given one voter's ranks
fun record_preferences(ranks: Array<Int>)
{
    for (i in 0 until candidate_count) {
        val preference = ranks[i]

        for (j in i + 1 until candidate_count) {
            val cand = ranks[j]
            preferences[preference][cand] += 1
        }
    }
    return
}

// Record pairs of candidates where one is preferred over the other
fun add_pairs()
{
    for (i in 0 until candidate_count) {
        for (j in 0 until candidate_count) {
            if (preferences[i][j] > preferences[j][i]) {
                pairs[pair_count] = pair(i, j)
                pair_count += 1
            }
        }
    }
    return
}

// Sort pairs in decreasing order by strength of victory
fun sort_pairs()
{
    for (i in 0 until pair_count) {

        var j = i - 1
        var current = pairs[i]
        var currentDifference = preferences[current.winner][current.loser] - preferences[current.loser][current.winner]


        while (j > - 1 && currentDifference > (preferences[pairs[j].winner][pairs[j].loser] - preferences[pairs[j].loser][pairs[j].winner])) {
            pairs[j + 1] = pairs[j]
            j -= 1
        }

        pairs[j + 1] = current
    }

    return
}

fun findCycle(start: Int, end: Int) : Boolean {
    if (start == end) {
        return true
    }

    if (locked[start][end]) {
        return true
    }

    for (i in 0 until candidate_count) {
        if (locked[start][i]) {
            if (findCycle(i, end)) {
                return true
            }
        }
    }

    return false
}

// Lock pairs into the candidate graph in order, without creating cycles
fun lock_pairs()
{
    for (i in 0 until pair_count) {
        var winner = pairs[i].winner
        var loser = pairs[i].loser

        if (!findCycle(loser, winner)) {
            locked[winner][loser] = true
        }
    }
    return
}

// Print the winner of the election
fun print_winner()
{
    for (i in 0 until candidate_count) {
        var source = true

        for (j in 0 until candidate_count) {
            if (locked[j][i]) {
                source = false
            }
        }

        if (source) {
            println(candidates[i])
            return
        }
    }
    return
}
