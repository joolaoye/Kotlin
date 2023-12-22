import kotlin.system.exitProcess

// Max voters and candidates
val MAX_VOTERS = 100
val MAX_CANDIDATES = 9

// preferences[i][j] is jth preference for voter i
var preferences = Array(MAX_VOTERS) {Array<Int>(MAX_CANDIDATES) {0} }

// Candidates have name, vote count, eliminated status
data class candidate(
    var name: String,
    var votes: Int,
    var eliminated: Boolean
)

// Array of candidates
var candidates = Array<candidate>(MAX_CANDIDATES){candidate("",0,false)}

// Numbers of voters and candidates
var voter_count: Int = 0
var candidate_count: Int = 0


fun main(argv: Array<String>)
{
    val argc = argv.size

    // Check for invalid usage
    if (argc < 1)
    {
        print("Usage: runoff [candidate ...]\n")
        exitProcess(1)
    }

    // Populate array of candidates
    candidate_count = argc

    if (candidate_count > MAX_CANDIDATES)
    {
        print("Maximum number of candidates is ${MAX_CANDIDATES}\n")
        exitProcess(2)
    }
    for (i in 0 until candidate_count)
    {
        candidates[i].name = argv[i]
    }

    voter_count = get_int("Number of voters: ")
    if (voter_count > MAX_VOTERS)
    {
        print("Maximum number of voters is MAX_VOTERS\n")
        exitProcess(3)
    }

    // Keep querying for votes
    for (i in 0 until voter_count)
    {

        // Query for each rank
        for (j in 0 until candidate_count)
        {
            val name: String = get_string("Rank ${j + 1}: ")

            // Record vote, unless it's invalid
            if (!vote(i, j, name))
            {
                print("Invalid vote.\n")
                exitProcess(4)
            }
        }

        print("\n")
    }

    // Keep holding runoffs until winner exists
    while (true)
    {
        // Calculate votes given remaining candidates
        tabulate()

        // Check if election has been won
        val won: Boolean = print_winner()
        if (won)
        {
            break
        }

        // Eliminate last-place candidates
        val min: Int = find_min()
        val tie: Boolean = is_tie(min)

        // If tie, everyone wins
        if (tie)
        {
            for (i in 0 until candidate_count)
            {
                if (!candidates[i].eliminated)
                {
                    print("${candidates[i].name}\n")
                }
            }
            break
        }

        // Eliminate anyone with minimum number of votes
        eliminate(min)

        // Reset vote counts back to zero
        for (i in 0 until candidate_count)
        {
            candidates[i].votes = 0
        }
    }

}

fun get_int(prompt: String) : Int {
    var number: Int
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
    val input = readln()

    return input
}

// Record preference if vote is valid
fun vote(voter: Int, rank: Int, name: String) : Boolean
{
    for (i in 0 until candidate_count) {
        if (name == candidates[i].name) {
            preferences[voter][rank] = i
            return true
        }
    }
    return false
}

// Tabulate votes for non-eliminated candidates
fun tabulate()
{
    for (i in 0 until voter_count) {
        for (j in 0 until candidate_count) {
            val candidateId = preferences[i][j]

            if (!candidates[candidateId].eliminated) {
                candidates[candidateId].votes += 1
            }
        }
    }
    return
}

// Print the winner of the election, if there is one
fun print_winner() : Boolean
{
    for (cand in candidates) {
        if (cand.votes * 2 > candidate_count) {
            println(cand.name)
            return true
        }
    }
    return false
}

// Return the minimum number of votes any remaining candidate has
fun find_min() : Int
{
    var min = candidates[0].votes

    for (i in 1 until candidate_count) {
        min = if (candidates[i].votes < min) candidates[i].votes else min
    }
    return min
}

// Return true if the election is tied between all candidates, false otherwise
fun is_tie(min: Int) : Boolean
{
    for (cand in candidates) {
        if (cand.votes != min) {
            return false
        }
    }
    return true
}

// Eliminate the candidate (or candidates) in last place
fun eliminate(min: Int)
{
    for (cand in candidates) {
        if (cand.votes == min) {
            cand.eliminated = true
        }
    }
    return
}